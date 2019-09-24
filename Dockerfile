FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/mini-lending-0.0.1-SNAPSHOT.jar app.jar
RUN touch /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]