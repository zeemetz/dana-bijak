FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/mini-lending-*.jar app.jar
RUN touch /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]