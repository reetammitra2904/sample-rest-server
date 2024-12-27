FROM openjdk:21-oracle
WORKDIR /app
COPY target/sample-rest-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]