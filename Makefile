.PONY: all build test

all: build

build:
	@./gradlew build --warning-mode all

run-tests:
	@./gradlew test --warning-mode all

test:
	@docker exec codelytv-ddd_skeleton-java ./gradlew test --warning-mode all

run:
	@./gradlew :run
