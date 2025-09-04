# Use Maven + JDK image to build WAR
FROM maven:3.9.2-eclipse-temurin-11 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Use Tomcat image to run WAR
FROM tomcat:9.0-jdk11
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/ITBBank.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
