FROM openjdk:17
ADD target/votingsystem-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Dserver.port=8080","app.jar"]