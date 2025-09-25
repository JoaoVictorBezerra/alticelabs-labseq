# Frontend - LabSeq Angular

## What's This?

A modern, responsive Angular application that provides a clean interface for calculating the LabSeq sequence. Built with the latest Angular features!

## Project Structure

```
src/app/
├── app.ts              # Main application component
├── app.html            # Main template
├── app.css             # Global styles
├── app.routes.ts       # Routing configuration
├── models/             # TypeScript interfaces
├── service/            # API services
└── utils/              # Utility functions
```

## Tech Stack

- **Angular 20** - Latest Angular framework
- **TypeScript 5.8** - Type-safe JavaScript
- **RxJS 7.8** - Reactive programming
- **Angular Forms** - Form handling and validation
- **Angular Router** - Client-side routing
- **Angular CLI** - Development tooling

## Getting Started

### Prerequisites

- **Node.js 18+** (LTS recommended)
- **npm** or **yarn** as package manager

### Development Setup

```bash
# Navigate to frontend directory
cd front/labseq-angular

# Install dependencies
npm install

# Start development server
npm start
```

The application will be available at `http://localhost:4200`

### Production Build

```bash
# Create production build
npm run build

# Build artifacts will be stored in the `dist/` directory
```

## Features

### LabSeq Calculator
- **Real-time calculation** of the LabSeq sequence
- **Input validation** with user-friendly error messages
- **BigInt support** for large numbers
- **Loading states** for better UX
- **Error handling** with detailed feedback

## Available Scripts

```bash
# Development server with hot reload
npm start

# Production build
npm run build

# Run unit tests
npm test
```

## Code Structure

### Main Component (`app.ts`)
The main application component handles:
- User input validation
- API communication
- Error state management
- Loading states
- Result display

### Service Layer (`service/labseq.ts`)
- **HTTP client** for API communication
- **Error handling** with RxJS operators
- **Type-safe** API responses

### Utility Functions (`utils/validation-utils.ts`)
- **Input validation** helpers
- **Number formatting** utilities
- **Error message** generators

## Testing

### Run Tests
```bash
# Run all tests
npm test

# Run tests in watch mode
npm test -- --watch

# Run tests with coverage
npm test -- --coverage
```

## API Integration

### Backend Communication
The frontend communicates with the Quarkus backend:

```typescript
// API endpoint configuration
private baseUrl = 'http://localhost:8080';

// HTTP service method
getLabSeq(term: number): Observable<ApiResponse<BigInt>> {
  return this.http.get<ApiResponse<BigInt>>(`${this.baseUrl}/labseq/${term}`);
}
```

### Error Handling
Comprehensive error handling for:
- **Network errors** (connection issues)
- **Server errors** (5xx responses)
- **Client errors** (4xx responses)
- **Validation errors** (input validation)
