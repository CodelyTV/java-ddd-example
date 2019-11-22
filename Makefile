.PONY: all build test

all: build

up:
	@docker-compose up -d

build:
	@./gradlew build --warning-mode all

run-tests:
	@./gradlew test --warning-mode all

test:
	@docker exec codelytv-ddd_skeleton-java ./gradlew test --warning-mode all

run:
	@./gradlew :run

# Start the app
start-mooc_backend:
	@./gradlew :run --args='mooc_backend server'

start-backoffice_frontend:
	@./gradlew :run --args='backoffice_frontend server'
