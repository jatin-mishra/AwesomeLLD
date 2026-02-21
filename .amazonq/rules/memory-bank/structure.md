# Project Structure

## Directory Organization

```
AwesomeLLD/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── CarRentalSystem/      # Car rental implementation
│   │   │   ├── ChessGame/            # Chess game implementation
│   │   │   ├── LoggingFramework/     # Logging system implementation
│   │   │   ├── ParkingLot/           # Parking lot (requirements only)
│   │   │   └── Main.java             # Entry point
│   │   └── resources/                # Application resources
│   └── test/
│       ├── java/                     # Test source files
│       └── resources/                # Test resources
├── gradle/                           # Gradle wrapper files
├── .amazonq/rules/                   # Amazon Q configuration
├── build.gradle                      # Build configuration
├── settings.gradle                   # Project settings
└── README.md                         # Design guidelines
```

## Core Components and Architecture

### 1. Car Rental System
**Location**: `src/main/java/org/example/CarRentalSystem/`

**Module Structure**:
- **booking/**: Booking lifecycle management
  - BookingOrder, BookingItem: Core booking entities
  - BookingService: Business logic for reservations
  - Status tracking: BookingOrderStatus, BookingItemStatus
  - Modification and cancellation handling

- **catalog/**: Vehicle catalog management
  - Car: Vehicle entity with specifications
  - CarType: Vehicle categorization
  - CatalogService: Catalog operations
  - Limit: Usage restrictions (km limits, etc.)

- **Inventory/**: Availability management
  - Inventory: Stock tracking
  - InventoryService: Availability operations
  - InventoryState: State management

- **pricing/**: Dynamic pricing system
  - PricingService: Price calculation logic
  - CarPrice, CarLevelPrice: Pricing models
  - PriceType: Pricing strategies (hourly, daily, weekly)

- **payments/**: Payment processing
  - PaymentService: Payment operations
  - PaymentIntent: Payment request representation
  - Ledger: Transaction recording
  - PaymentStatus, PaymentType: Payment metadata

- **filter/**: Search and filtering
  - Filter: Filter interface
  - FilterProxy: Filter orchestration
  - FilterQuery: Query representation
  - FilterType: Available filter types

- **user/**: User management
  - User: User entity
  - Contact: Contact information
  - UserService: User operations

### 2. Chess Game
**Location**: `src/main/java/org/example/ChessGame/`

**Components**:
- **Game Management**: Game, GameManager, GameState
- **Board Representation**: Board, Cell
- **Pieces**: Piece, PieceType, Color
- **Players**: Player
- **API**: GameStartResponse, Main

**Architecture Pattern**: State management with entity-based design

### 3. Logging Framework
**Location**: `src/main/java/org/example/LoggingFramework/`

**Module Structure**:
- **appender/**: Output destinations
  - Appender interface
  - AppenderFactory: Factory pattern for appender creation
  - FileAppender: File-based logging
  - AppenderType: Appender types enumeration

- **formatter/**: Log formatting
  - Formatter interface
  - FormatterFactory: Factory pattern for formatter creation
  - JsonFormatter: JSON output formatting
  - Layout: Layout configuration

- **config/**: Configuration management
  - LogConfig: Main configuration
  - AppenderConfig: Appender settings
  - LayoutConfig, JsonLayoutConfig: Layout settings

- **Core**: Logger, LogLevel, MDC

**Architecture Pattern**: Factory pattern with strategy pattern for formatting

### 4. Parking Lot System
**Location**: `src/main/java/org/example/ParkingLot/`

**Status**: Requirements documented, implementation pending

## Architectural Patterns

### Domain-Driven Design
- Clear separation of business domains (booking, pricing, inventory)
- Each module encapsulates its own business logic
- Service layer for business operations

### Service-Oriented Architecture
- Service interfaces define contracts (BookingService, PricingService, etc.)
- Implementation classes separate from interfaces
- Loose coupling between modules

### Factory Pattern
- Used extensively in LoggingFramework (AppenderFactory, FormatterFactory)
- Enables extensibility without modifying existing code

### Strategy Pattern
- Pricing strategies (PriceType)
- Payment types (PaymentType)
- Filter types (FilterType)

### State Pattern
- Booking states (BookingOrderStatus, BookingItemStatus)
- Payment states (PaymentStatus)
- Inventory states (InventoryState)
- Game states (GameState)

## Component Relationships

### Car Rental System Flow
```
User → BookingService → InventoryService → Catalog
                     ↓
                PricingService → PaymentService → Ledger
                     ↓
                FilterService (for search)
```

### Logging Framework Flow
```
Logger → LogConfig → AppenderFactory → Appender (File/Console)
                  ↓
              FormatterFactory → Formatter (JSON/Text)
```

## Build System
- **Build Tool**: Gradle 9.2.0
- **Language**: Java
- **Testing**: JUnit 5 (Jupiter)
- **Package Structure**: org.example.*
