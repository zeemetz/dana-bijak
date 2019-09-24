FROM java:8
VOLUME /tmp
ADD build/libs/mini-lending-0.0.1-SNAPSHOT.jar mini-lending.jar
RUN bash -c 'touch /spring-redis-docker-example.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mini-lending.jar"]