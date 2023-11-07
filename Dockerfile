FROM openjdk:21-slim-buster
WORKDIR /app

RUN apt update
RUN apt install -y npm
