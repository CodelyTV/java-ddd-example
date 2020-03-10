# git diff --name-status origin/master | grep "src/" | awk -F '/' '{print $2}' | uniq

teams=$(yq r .github/monorepo_assign.yml assign | awk -F":" '{print $1}')
for team in "${teams[@]}"; do
  :
  echo $team
  yq r .github/monorepo_assign.yml assign.$team
#  directories=$(yq r .github/monorepo_assign.yml "\'assign.$team\'")
#  printf '%s\n' "${directories[@]}"
#
#  for directory in "${directories[@]}"; do
#    :
#    echo "$directory"
#  done
done
