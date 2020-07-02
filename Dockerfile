# maven build
FROM maven:3.6.0-jdk-11-slim AS build
COPY gameapi/context /home/app/context
COPY gameapi/domain /home/app/domain
COPY gameapi/core /home/app/core
COPY gameapi/webapp /home/app/webapp
COPY gameapi/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# openjdk 11 run
FROM amazoncorretto:11
COPY --from=build /home/app/webapp/target/webapp-0.0.1-SNAPSHOT.jar /usr/local/lib/gameapi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/gameapi.jar"]
