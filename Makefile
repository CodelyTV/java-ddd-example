.PONY: all build test

all: build

build:
	@./gradlew build --warning-mode all

test:
	@./gradlew test --warning-mode all

run:
	@./gradlew :run
