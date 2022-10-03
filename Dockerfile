FROM amazoncorretto:17-alpine-jdk

VOLUME /tmp

COPY /target/java-multithreading-and-docker-1.0-SNAPSHOT-launcher.jar app.jar

ENTRYPOINT exec java -jar /app.jar