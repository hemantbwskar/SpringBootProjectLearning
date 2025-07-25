# Use a base image with Java Runtime Environment (JRE)
FROM openjdk:17-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/learning-0.0.1-SNAPSHOT.jar /app/learning-0.0.1-SNAPSHOT.jar

# Expose the port your application listens on (e.g., 8080 for Spring Boot)
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "learning-0.0.1-SNAPSHOT.jar"]