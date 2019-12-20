FROM openjdk:12-jdk-alpine

RUN mkdir -p /apps
COPY ./target/TestingForDINOSystem-1.0-SNAPSHOT.jar /apps/app.jar
COPY ./entrypoint.sh /apps/entrypoint.sh

RUN chmod +x /apps/entrypoint.sh
EXPOSE 8080
CMD ["/apps/entrypoint.sh"]