# Build Stage
FROM maven:4.0.0-rc-5-sapmachine-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:17-ea-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]