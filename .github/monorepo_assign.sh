directory_has_been_modified() {
  local directory=$1
  local changed_directories=$(git diff --name-status origin/master | grep "src/" | awk -F '/' '{print $2}' | uniq)

  [[ $changed_directories == *"$directory"* ]]
}

team_has_been_modified() {
  local IFS=$'\n'
  local directories=($1)
  local i
  for (( i=0; i<${#directories[@]}; i++ )) ; do
    local directory_without_yaml=$(echo "${directories[$i]//'- '}")

    if directory_has_been_modified "$directory_without_yaml" ; then
        echo "👍 $directory_without_yaml directory HAS been modified"
    else
        echo "👎 $directory_without_yaml directory has NOT been modified"
    fi
  done
}

assign_to() {
  local IFS=$'\n'
  local teams=($1)
  local i
  for (( i=0; i<${#teams[@]}; i++ )) ; do
    echo "$i: ${teams[$i]}"

    echo "---------------------------------"
    directories=$(yq r -X monorepo_assign.yml "assign.${teams[$i]}")

    team_has_been_modified "$directories"

    echo "---------------------------------"
  done
}

teams=$(yq r monorepo_assign.yml assign | awk -F":" '{print $1}')
assign_to "$teams"
