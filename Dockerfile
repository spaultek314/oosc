# Step 1: Build the Maven application using Java 21
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the compiled jar file using a slim Java 21 runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# Render dynamically assigns a port, so we pass it to Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]
