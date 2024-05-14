# Stage 1: Build and configure the Flutter web application
FROM cirrusci/flutter:latest AS flutter-builder

# Set the working directory
WORKDIR /app

# Copy your flutter project to the container
COPY . .

# Ensure root user to modify ownership and permissions
USER root

# Optionally create the flutter user if it doesn't exist
# Comment out the next two lines if the flutter user already exists in the image
RUN groupadd -r flutter && useradd -r -g flutter -G sudo -m -s /bin/bash flutter

# Change ownership and permissions of the Flutter SDK
RUN chown -R flutter:flutter /sdks/flutter && chmod -R 755 /sdks/flutter

# Configure Git to consider the SDK directory as safe
RUN git config --global --add safe.directory /sdks/flutter

# Switch to non-root user, now that 'flutter' is confirmed to exist
USER flutter

# Get all Flutter dependencies
RUN flutter pub get

# Build the Flutter web application
RUN flutter build web

# Stage 2: Build the Spring Boot application
FROM maven:3.6.3-jdk-11 AS spring-boot-builder

# Set the working directory for Spring Boot application
WORKDIR /app

# Copy the Spring Boot project files to the container
# Ensure this copies the directory containing pom.xml
COPY ./src /app

# Run Maven package without running tests
RUN mvn package -DskipTests

# Stage 3: Serve the Flutter application with Nginx
FROM nginx:alpine AS final-stage

# Copy the Flutter web build from the Flutter builder stage
COPY --from=flutter-builder /app/build/web /usr/share/nginx/html

# Copy the Spring Boot executable JAR from the Spring Boot builder stage
# Adjust the source path according to where your JAR file is located in the Spring Boot builder stage
COPY --from=spring-boot-builder /app/target/*.jar /app.jar

# Expose HTTP port
EXPOSE 80

# Start Nginx and keep it running
CMD ["nginx", "-g", "daemon off;"]
