# Use an official Maven image with JDK included
FROM maven:3.6.3-openjdk-11 as builder

# Set the working directory in the Docker image
WORKDIR /app

# Copy only pom.xml initially to leverage Docker cache
COPY ./pom.xml /app

# Fetch all dependencies
RUN mvn dependency:go-offline -B

# Copy the source code into the Docker image
COPY ./src /app/src

# Package the application
RUN mvn package -DskipTests

# Start with a clean smaller image without Maven and JDK
FROM openjdk:11-jdk-slim

WORKDIR /app

# Copy the built artifact from the previous stage
COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
