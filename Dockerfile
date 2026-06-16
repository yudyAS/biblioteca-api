# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins

# Copy project files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Set default port (can be overridden by PORT environment variable)
ENV PORT=8080

# Copy JAR from build stage
COPY --from=builder /build/target/biblioteca-api-*.jar app.jar

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD java -cp app.jar org.springframework.boot.loader.JarLauncher || exit 1

# Expose port
EXPOSE ${PORT}

# Run the application
CMD ["java", "-jar", "app.jar"]
