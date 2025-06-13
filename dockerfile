# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -pl auth -am -DskipTests -Dgit.commit.id.skip=true

# Etapa 2: Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/auth/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]