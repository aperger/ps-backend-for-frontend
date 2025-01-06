# Use an official Maven image as the base image
FROM maven:3.9.9-eclipse-temurin-17 AS build
# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY ps-bff/src ps-bff/src
COPY ps-bff/pom.xml ps-bff/pom.xml
# COPY bff-jpa/src axing55-jpa/src
# COPY bff-jpa/pom.xml axing55-jpa/pom.xml
# Build the application using Maven

RUN mvn -U clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM eclipse-temurin:17-jdk
# Set the working directory in the container
WORKDIR /opt
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/ps-bff/target/ps-bff-*.jar ./app.jar
# Set the command to run the application
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=rancher"]