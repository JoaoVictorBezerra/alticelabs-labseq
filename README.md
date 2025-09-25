# Technical Test - LabSeq Calculator

## What's This About?

This project is a web application for calculating the LabSeq sequence, built as part of a technical assessment. It's got a Java backend (Quarkus) and an Angular frontend working together.

## The LabSeq Sequence

The LabSeq sequence is defined by these rules:
- `l(0) = 0`
- `l(1) = 1` 
- `l(2) = 0`
- `l(3) = 1`
- `l(n) = l(n-4) + l(n-3)` for n ≥ 4

## Project Structure

```
labseq/
├── backend/          # Java REST API (Quarkus)
├── front/           # Angular frontend
└── docker-compose.yml # Containerization setup
```

## Tech Stack

### Backend
- **Java 21** with **Quarkus 3.27.0**
- **RESTEasy** for REST APIs
- **Hibernate Validator** for validation
- **OpenAPI** for documentation
- **ConcurrentHashMap** for caching

### Frontend
- **Angular 20** with **TypeScript**
- **RxJS** for reactive programming
- **Modern CSS** with responsive design
- **BigInt** for large number support

## Getting Started

### Option 1: Docker (Easiest Way)

```bash
# Clone the repo
git clone https://github.com/JoaoVictorBezerra/alticelabs-labseq.git
cd labseq

# Fire it up with Docker Compose
docker-compose up --build
```

Once it's running:
- Backend will be at: `http://localhost:8080`
- Frontend will be at: `http://localhost:4200`

### Option 2: Manual Setup

#### Backend

```bash
cd backend

# Make sure you have Java 21 installed
java --version

# Run in development mode
./mvnw quarkus:dev
```

#### Frontend

```bash
cd front/labseq-angular

# Install dependencies
npm install

# Start the dev server
npm start
```

## API Documentation

Once the backend is running, you can check out the interactive API docs at:
`http://localhost:8080/q/swagger-ui`

### Available Endpoints

- **GET** `/labseq/{n}` - Calculates the nth term of the LabSeq sequence
- **GET** `/health` - Health check endpoint

### Example Usage

```bash
curl http://localhost:8080/labseq/10
```

Response:
```json
{
  "data": 2,
  "message": "Successfully calculated",
  "success": true
}
```

## What's Under the Hood

- **Smart caching**: Cache system for better performance
- **Big number support**: Uses BigInt for large calculations
- **Modern UI**: Responsive Angular interface
- **Docker ready**: Easy deployment with containers
- **Input validation**: Proper error handling and validation
- **Well tested**: Comprehensive unit test coverage

## Running Tests

### Backend Tests
```bash
cd backend
./mvnw test
```

## Technical Highlights

### Performance
- Iterative implementation to avoid stack overflow
- ConcurrentHashMap cache for thread-safe operations
- BigInteger support for arbitrarily large numbers

### Architecture
- Backend: Layered architecture concepts
- Frontend: Well-structured services.
- Communication: REST API with robust error handling

### Validation
- Backend input validation (non-negative numbers)
- Frontend form validation
- Network and server error handling

