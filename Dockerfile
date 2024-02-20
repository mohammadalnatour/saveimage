#
# Build stage
#
FROM maven:3.9.6-ibm-semeru-21-jammy AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:21-jdk-slim-buster
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8087
EXPOSE 8087
ENTRYPOINT ["java","-jar","demo.jar"]