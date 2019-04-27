FROM openjdk:8u102-jre

COPY build/libs/poc-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT java -jar app.jar && tail -f /dev/null