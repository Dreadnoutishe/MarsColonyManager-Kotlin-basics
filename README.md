# Mars Colony Manager

Mars Colony Manager is a console-based Kotlin application created to demonstrate fundamental Kotlin programming concepts and object-oriented programming.

The application simulates the management of a small colony on Mars. The user can monitor colony resources, manage crew members, complete missions, advance through Martian sols, and keep the colony operational.

## Features

- Monitor colony resources such as oxygen, energy, and food
- Manage different types of colony crew members
- View and complete Mars missions
- Missions provide resource rewards to the colony
- Advance through Martian sols
- Colony resources decrease over time
- Colony failure occurs if a critical resource is depleted
- Mission execution is simulated asynchronously using Kotlin coroutines

## Kotlin Concepts Demonstrated

The project demonstrates:

- Variables and Kotlin data types
- Conditionals (`if`, `when`)
- Loops (`for`, `while`)
- Collections (`List`, `Set`, `Map`)
- Collection operations (`map`, `filter`, `reduce`)
- Functions
- Higher-order functions
- Lambdas
- Classes and objects
- Inheritance
- Interfaces
- Polymorphism
- Data classes
- Sealed classes
- Kotlin coroutines
- Suspend functions

## Project Structure

```text
src/main/kotlin/
├── Main.kt
├── Colony.kt
├── Crew.kt
├── Mission.kt
└── MissionService.kt
```

## Technologies

- Kotlin
- Kotlin Coroutines
- Gradle
- IntelliJ IDEA

## Running the Project

Run the `main()` function located in `Main.kt` using IntelliJ IDEA.

Alternatively, the project can be executed using the Gradle wrapper.

### Windows

```bash
gradlew.bat run
```

### Linux / macOS

```bash
./gradlew run
```

## Purpose

This project was developed as an assignment for practicing Kotlin fundamentals and object-oriented programming concepts required for Android development.