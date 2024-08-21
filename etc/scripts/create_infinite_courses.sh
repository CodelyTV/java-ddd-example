function create_course() {
    eventId=$(uuidgen)
    courseId=$(uuidgen)
    courseName=$(shuf -n3 /usr/share/dict/words | xargs)
    courseDuration=$((1 + RANDOM % 1000))

    curl -X POST --location "http://localhost:8030/domain-events" \
        -H "Content-Type: application/json" \
        -d '{
              "eventName": "course.created",
              "eventRaw": {
                "data": {
                  "id": "'"$eventId"'",
                  "type": "course.created",
                  "occurred_on": "2023-11-14 10:00:00",
                  "attributes": {
                    "id": "'"$courseId"'",
                    "name": "'"$courseName"'",
                    "duration": "'"$courseDuration"' days"
                  }
                },
                "meta": {
                }
              }
            }'

    echo "Created: $courseName"
}

while true; do
    create_course &
    sleep 0.001
done
