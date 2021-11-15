.PHONY: all
all: build

.PHONY: up
up:
	@docker-compose up -d

.PHONY: build
build:
	@./gradlew build --warning-mode all

.PHONY: run-tests
run-tests:
	@./gradlew test --warning-mode all

.PHONY: test
test:
	@docker exec codelytv-ddd_skeleton-java ./gradlew test --warning-mode all

.PHONY: run
run:
	@./gradlew :run

.PHONY: ping-mysql
ping-mysql:
	@docker exec codelytv-java_ddd_skeleton-mysql mysqladmin --user=root --password= --host "127.0.0.1" ping --silent

# Start the app
.PHONY: start-mooc_backend
start-mooc_backend:
	@./gradlew :run --args='mooc_backend server'

.PHONY: start-backoffice_frontend
start-backoffice_frontend:
	@./gradlew :run --args='backoffice_frontend server'
