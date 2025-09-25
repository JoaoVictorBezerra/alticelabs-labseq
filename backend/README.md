# Backend - LabSeq API

## What's This?

A REST API built with Java and Quarkus for calculating the LabSeq sequence. Pretty straightforward stuff!

## Architecture

The backend follows clean architecture principles with a clear separation of concerns:

```
src/main/java/io/github/joaovictorbezerra/
├── controller/          # REST endpoints
├── service/            # Business logic
├── dto/               # Data transfer objects
├── exception/         # Custom exceptions and handlers
├── constants/         # API constants
└── config/           # Configuration classes
```

## Tech Stack

- **Java 21** - Latest LTS with modern features
- **Quarkus 3.27.0** - Optimized Java framework
- **RESTEasy** - JAX-RS implementation for REST APIs
- **Hibernate Validator** - Bean validation
- **OpenAPI** - Interactive API documentation
- **Maven** - Dependency management and build tool

## Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.8+** (or use the included `mvnw` wrapper)

### Development Mode

```bash
# Navigate to backend directory
cd backend

# Start Quarkus in development mode (with hot reload)
./mvnw quarkus:dev
```

The API will be available at `http://localhost:8080`

### Production Build

```bash
# Create JAR file
./mvnw package

# Run the JAR
java -jar target/quarkus-app/quarkus-run.jar
```

### Native Compilation

```bash
# Build native executable
./mvnw package -Pnative

# Run the native executable
./target/labseq-1.0-SNAPSHOT-runner
```

## API Endpoints

### Main Endpoints

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| `GET` | `/labseq/{n}` | Calculates the nth term of the LabSeq sequence | `/labseq/10` |
| `GET` | `/health` | Health check | `/health` |

### Interactive Documentation

Check out `http://localhost:8080/q/swagger-ui` for interactive API documentation.

### Example Call

```bash
curl -X GET "http://localhost:8080/labseq/10" \
     -H "accept: application/json"
```

**Response:**
```json
{
  "data": 2,
  "message": "Successfully calculated",
  "success": true
}
```

## Performance Optimizations

### Smart Caching System
- **ConcurrentHashMap** for thread-safe caching
- Pre-computed base cases (0, 1, 2, 3)
- Automatic cache population during computation
- Efficient storage of BigInteger values

### Algorithm Optimization
- **Iterative approach** instead of recursive (no stack overflow!)
- **BigInteger** support for arbitrarily large numbers
- **Thread-safe** concurrent operations

## Testing

### Run LabSeq Test Class
```bash
./mvnw test -Dtest=LabSeqServiceTest
```

### Test Coverage
The project includes comprehensive unit tests covering:
- ✅ Service layer logic
- ✅ Edge cases and error scenarios
- ✅ Cache behavior
- ✅ Input validation

## Configuration

### Application Properties
Located in `src/main/resources/application.properties`:

## Error Handling

The API includes comprehensive error handling:

- **400 Bad Request** - Invalid input parameters
- **500 Internal Server Error** - Server errors
- **Custom exceptions** for business logic errors

### Error Response Format
```json
{
  "error": "Invalid term number: -1",
  "message": "Please provide a non-negative integer",
  "success": false
}
```

## Technical Implementation

### LabSeq Algorithm
- Iterative implementation to avoid stack overflow
- ConcurrentHashMap cache for performance
- BigInteger support for large numbers
- Input validation with Hibernate Validator

### Code Structure
- **Controller**: REST endpoints with validation
- **Service**: Business logic and caching
- **DTO**: Data transfer objects
- **Exception Handler**: Global error handling

