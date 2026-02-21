# Development Guidelines

## Code Quality Standards

### Package Organization
- Follow hierarchical package structure: `org.example.<SystemName>.<Module>`
- Group related classes by functional domain (booking, payments, catalog, etc.)
- Each module should be self-contained with clear responsibilities

### Naming Conventions
- **Classes**: PascalCase (e.g., `BookingService`, `PaymentIntent`)
- **Interfaces**: PascalCase without "I" prefix (e.g., `BookingService`, not `IBookingService`)
- **Enums**: PascalCase with PascalCase values (e.g., `GameState.Created`, `PriceType.BasePrice`)
- **Variables**: camelCase (e.g., `bookingId`, `userId`)
- **Private Fields**: camelCase without prefix (e.g., `name`, `logConfig`)
- **Methods**: camelCase, verb-based (e.g., `checkAvailability()`, `reserve()`)

### File Structure
- One public class per file
- File name matches the class name
- Minimal or no comments (self-documenting code preferred)
- Keep classes focused and concise

## Semantic Patterns

### 1. Enum Usage for State and Type Management (5/5 files)
**Pattern**: Use enums extensively for representing states, types, and categories

**Examples**:
```java
// State management
public enum GameState {
    Created,
    Started,
    Finished
}

public enum BookingItemStatus {
    Confirmed,
    Cancelled,
    Refunded,
    Picked,
    Dropped
}

public enum PaymentStatus {
    Initiated,
    Success,
    Failed,
    Refunded
}

// Type categorization
public enum PriceType {
    BasePrice,
    WeekendSurcharge,
    LimitBreachPerKm
}
```

**When to Use**:
- Fixed set of states in a lifecycle
- Type categorization (payment types, car types, appender types)
- Strategy selection (pricing strategies, filter types)

### 2. Factory Pattern for Object Creation (2/2 factory files)
**Pattern**: Use static factory classes with private constructors for centralized object creation

**Example**:
```java
public class AppenderFactory {
    private AppenderFactory(){} // Private constructor
    
    private static Map<AppenderType, Appender> appenderMap = new ConcurrentHashMap<>();
    
    public static void registerAppender(Map<AppenderType, AppenderConfig> appenderConfigList){
        for(var appender : appenderConfigList.entrySet()){
            appenderMap.computeIfAbsent(appender.getKey(), x -> getAppender(appender.getValue()));
        }
    }
    
    private static Appender getAppender(AppenderConfig appenderConfig){
        return switch (appenderConfig.getAppender()){
            case File -> new FileAppender(appenderConfig);
            default -> throw new UnsupportedOperationException("Only File appender is supported");
        };
    }
}
```

**Key Characteristics**:
- Private constructor to prevent instantiation
- Static methods for object creation
- Internal registry/cache using ConcurrentHashMap
- Switch expressions for type-based creation
- Registration pattern for configuration

### 3. Service Interface Pattern (2/2 service files)
**Pattern**: Define service contracts through interfaces, separate from implementation

**Example**:
```java
public interface BookingService {
    List<Car> checkAvailability(FilterQuery query);
    String reserve(String carId, String userId, Instant from, Instant to);
    ModificationInfo modificationIntent(String bookingId, Instant from, Instant to);
    String modify(ModificationInfo info);
    CancellationInfo cancellationIntent(String bookingId);
    String cancel(CancellationInfo info);
}
```

**Characteristics**:
- Return domain objects or primitives (String IDs)
- Use Instant for timestamps
- Intent-based methods for two-phase operations (intent → action)
- Clear, verb-based method names

### 4. Immutable Data Objects with Getters/Setters
**Pattern**: Simple POJOs with private fields and public accessors

**Example**:
```java
public class Car {
    private String id;
    private CarType type;
    private String model;
    private String plateNumber;
    private Limit limit;

    public String getId() { return id; }
    public CarType getType() { return type; }
    public void setLimit(Limit limit) { this.limit = limit; }
}
```

**Characteristics**:
- Private fields
- Public getters for all fields
- Selective setters (only for mutable properties)
- No business logic in entity classes

### 5. Simplicity Over Complexity
**Pattern**: Prefer simple solutions, explicitly document trade-offs

**Example**:
```java
public class PaymentIntent {
    private String nonce;
    private Double money;
    // private Currency currency; // avoiding multiple currency
}
```

**Philosophy**:
- Comment out complexity that's not needed yet
- Avoid over-engineering for future requirements
- Document why certain features are omitted
- Prefer Double over BigDecimal when precision isn't critical

### 6. Placeholder Implementation with TODO Comments
**Pattern**: Stub out methods with comments for later implementation

**Example**:
```java
public void move(String player, int from_x, int from_y, int to_x, int to_y){
    // validate player and current player
    // get piece
    // validate piece move is valid, check for stalemate and checkmate
    // move and kill if needed
    // check for staleMate or checkmate
}

public boolean isStaleMate(){return true;}
public boolean isCheckMate(){return true;}
```

**When to Use**:
- During initial implementation to maintain flow
- To document intended logic without breaking compilation
- To communicate design intent to reviewers

### 7. Constructor-Based Initialization
**Pattern**: Initialize objects through constructors with all required dependencies

**Example**:
```java
public Logger(Class<?> clzz, LogConfig config){
    this.name = clzz.getName();
    this.logConfig = config;
    AppenderFactory.registerAppender(config.getAppenders());
    appenders = AppenderFactory.getAppenders(config.getAppenders().keySet());
}

public Game(String[] names){
    Player player1 = new Player(names[0], Color.White);
    Player player2 = new Player(names[1], Color.Black);
    this.whitePieces = new HashMap<>(generatePieces(Color.Black));
    this.blackPieces = new HashMap<>(generatePieces(Color.White));
    this.board = new Board(blackPieces, whitePieces);
    this.players = new Player[]{player1, player2};
    this.current = player1;
    this.state = GameState.Created;
}
```

**Characteristics**:
- All initialization in constructor
- No separate init() methods
- Immutable after construction (where possible)
- Factory registration during construction

### 8. Event-Driven Logging Pattern
**Pattern**: Log as structured events (Map-based) rather than strings

**Example**:
```java
private void log(LogLevel level, String content, Throwable th){
    if(!level.isEligible(this.logConfig.getLevel(this.name))) return;

    final Map<String, Object> event = new HashMap<>(Map.of(
            "level", level,
            "content", content
    ));
    if(th != null) event.put("exception", th);
    for(Appender appender : appenders){
        appender.append(event);
    }
}
```

**Characteristics**:
- Events as Map<String, Object>
- Conditional field inclusion (exception only if present)
- Level-based filtering before processing
- Multiple appenders for same event

## Design Principles Applied

### 1. Single Responsibility Principle
- Each class has one clear purpose
- Services handle business logic
- Entities represent data
- Factories handle creation

### 2. Open/Closed Principle
- Factory pattern allows extension without modification
- Enum-based strategies enable new types
- Interface-based services allow multiple implementations

### 3. Dependency Inversion
- Depend on interfaces (BookingService) not implementations
- Factory pattern abstracts creation logic

### 4. Pragmatic Simplicity
- Break principles when it adds unnecessary complexity
- Document trade-offs explicitly
- Prefer working code over perfect architecture

## Common Idioms

### Switch Expressions (Java 14+)
```java
return switch (appenderConfig.getAppender()){
    case File -> new FileAppender(appenderConfig);
    default -> throw new UnsupportedOperationException("Only File appender is supported");
};
```

### Map.of() for Immutable Maps
```java
final Map<String, Object> event = new HashMap<>(Map.of(
    "level", level,
    "content", content
));
```

### computeIfAbsent for Lazy Initialization
```java
appenderMap.computeIfAbsent(appender.getKey(), x -> getAppender(appender.getValue()));
```

### Stream API for Filtering
```java
return appenderMap.values().stream()
    .filter(appender -> type.contains(appender.getType()))
    .toList();
```

## Concurrency Considerations

### Thread-Safe Collections
- Use ConcurrentHashMap for shared registries
- Example: `private static Map<AppenderType, Appender> appenderMap = new ConcurrentHashMap<>();`

### Immutability
- Prefer immutable objects where possible
- Use final for fields that shouldn't change
- Return copies of mutable collections

## Testing Approach

### Test Structure
- Tests in src/test/java/ mirroring main structure
- JUnit 5 (Jupiter) for test framework
- Use @Test annotation for test methods

## Requirements Documentation

### Req.md Pattern
- Each system has a Req.md file documenting requirements
- Separate requirements into: must-have, good-to-have, out-of-scope
- Document edge cases and error handling upfront
- Keep requirements with implementation code

## Anti-Patterns to Avoid

1. **Over-Engineering**: Don't create abstractions for hypothetical future requirements
2. **Premature Optimization**: Get working code first, optimize later
3. **Complex Inheritance**: Prefer composition and interfaces over deep hierarchies
4. **Magic Numbers**: Use enums or constants for fixed values
5. **God Classes**: Keep classes focused on single responsibility

## Code Review Checklist

- [ ] Follows package naming convention
- [ ] Uses appropriate enums for states/types
- [ ] Service interfaces separate from implementations
- [ ] Factory pattern for complex object creation
- [ ] Proper use of access modifiers (private fields, public methods)
- [ ] No unnecessary comments (code is self-documenting)
- [ ] Thread-safety considered for shared state
- [ ] Simplicity preferred over complexity
- [ ] Trade-offs documented in comments
