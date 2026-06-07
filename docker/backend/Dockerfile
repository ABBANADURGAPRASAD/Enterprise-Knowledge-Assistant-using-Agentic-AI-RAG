FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY backend-api/pom.xml backend-api/
COPY backend-service/pom.xml backend-service/
COPY backend-app/pom.xml backend-app/
COPY backend-api/src backend-api/src
COPY backend-service/src backend-service/src
COPY backend-app/src backend-app/src
RUN apk add --no-cache maven && \
    mvn clean package -pl backend-app -am -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/backend-app/target/backend-app-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
