FROM node:22 AS builder1


WORKDIR /client

COPY /client .

RUN npm ci
RUN npm i -g @angular/cli
RUN ng build

FROM openjdk:23 AS builder2

WORKDIR /server

COPY /server .

COPY --from=builder1 /client/dist/client/browser /server/src/main/resources/static

RUN chmod a+x mvnw
RUN ./mvnw install -DskipTests

FROM openjdk:23

WORKDIR /app

COPY --from=builder2 /server/target/*.jar app.jar

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar /app/app.jar -Dserver.port=${PORT}