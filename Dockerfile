# Use an official OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Run the built jar (replace with your actual jar name)
CMD ["java", "-jar", "target/currency-0.0.1-SNAPSHOT.jar"]
