# Use the official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/jooq-with-flyway-migration-demo.jar /app/app.jar

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Define the default command to run your application
CMD ["java", "-jar", "app.jar"]