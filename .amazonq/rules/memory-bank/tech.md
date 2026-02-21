# Technology Stack

## Programming Languages

### Java
- **Primary Language**: Java
- **Package Structure**: org.example.*
- **Java Features Used**:
  - Object-oriented programming
  - Interfaces and abstract classes
  - Enumerations
  - Factory pattern implementations
  - Collections framework

## Build System

### Gradle
- **Version**: 9.2.0
- **Build File**: build.gradle
- **Configuration**:
  ```gradle
  plugins {
      id 'java'
  }
  
  group = 'org.example'
  version = '1.0-SNAPSHOT'
  ```

### Gradle Wrapper
- **Included**: Yes (gradlew, gradlew.bat)
- **Location**: gradle/wrapper/
- **Purpose**: Ensures consistent Gradle version across environments

## Dependencies

### Testing Framework
- **JUnit Platform**: 5.10.0
- **JUnit Jupiter**: Test implementation
- **JUnit Platform Launcher**: Test runtime

### Dependency Management
- **Repository**: Maven Central
- **Configuration**:
  ```gradle
  dependencies {
      testImplementation platform('org.junit:junit-bom:5.10.0')
      testImplementation 'org.junit.jupiter:junit-jupiter'
      testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
  }
  ```

## Development Commands

### Build Commands
```bash
# Build the project
./gradlew build

# Clean build artifacts
./gradlew clean

# Compile Java sources
./gradlew compileJava

# Compile test sources
./gradlew compileTestJava
```

### Test Commands
```bash
# Run all tests
./gradlew test

# Run tests with detailed output
./gradlew test --info

# Run specific test class
./gradlew test --tests "ClassName"
```

### Run Commands
```bash
# Run main application (if configured)
./gradlew run

# Run specific main class
java -cp build/classes/java/main org.example.Main
```

### Gradle Tasks
```bash
# List all available tasks
./gradlew tasks

# Show project dependencies
./gradlew dependencies

# Show project properties
./gradlew properties
```

## Project Configuration

### Settings
- **Project Name**: AwesomeLLD (defined in settings.gradle)
- **Group ID**: org.example
- **Version**: 1.0-SNAPSHOT

### Source Structure
- **Main Sources**: src/main/java/
- **Test Sources**: src/test/java/
- **Resources**: src/main/resources/, src/test/resources/

### Build Output
- **Build Directory**: build/
- **Compiled Classes**: build/classes/java/main/
- **Test Classes**: build/classes/java/test/
- **Reports**: build/reports/

## IDE Integration

### Amazon Q
- **Configuration Directory**: .amazonq/rules/
- **Memory Bank**: .amazonq/rules/memory-bank/
- **Purpose**: AI-assisted development with project context

### Gradle Integration
- Most modern IDEs (IntelliJ IDEA, Eclipse, VS Code) support Gradle natively
- Import as Gradle project for automatic dependency resolution
- Gradle wrapper ensures consistent build environment

## Version Control

### Git
- **Ignore File**: .gitignore present
- **Ignored Directories**: 
  - .gradle/ (Gradle cache)
  - build/ (Build outputs)
  - Standard IDE files

## Development Environment

### Requirements
- **JDK**: Java Development Kit (version not explicitly specified, recommend JDK 11+)
- **Gradle**: Managed via wrapper (no manual installation needed)
- **IDE**: Any Java-compatible IDE (IntelliJ IDEA, Eclipse, VS Code)

### Setup Steps
1. Clone the repository
2. Ensure JDK is installed and JAVA_HOME is set
3. Run `./gradlew build` to download dependencies and build
4. Import into IDE as Gradle project
5. Run tests with `./gradlew test`

## Testing Configuration

### JUnit 5 Setup
```gradle
test {
    useJUnitPlatform()
}
```

### Test Structure
- Test files located in src/test/java/
- Follow same package structure as main sources
- Use JUnit Jupiter annotations (@Test, @BeforeEach, etc.)
