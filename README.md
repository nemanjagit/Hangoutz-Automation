# Hangoutz - Event Planner App, Test Automation

## Overview

This repository contains the test automation framework developed for the **Event Planner App** during an internship at Levi9. Built using **Java**, **TestNG**, and **Appium**, the framework ensures robust and reliable testing across Android and iOS platforms.

The framework covers all major screens and functionalities, ensuring a seamless user experience.

## Features

- **Comprehensive Test Coverage**:
  - **Event Management**: Event creation, editing, and deletion.
  - **Friends Module**: Friend management and interactions.
  - **Profile/Settings**: User profile updates and settings adjustments.
  - **Authentication**: Login and registration workflows.
  - **End-to-End (E2E) Tests**: Full user journeys covering multiple app features.
- **Platform Support**: Automation tests for both Android and iOS platforms.
- **Reusable Test Methods**:
  - Utilities for visibility checks, element interaction, keyboard handling, and swiping gestures.
  - Custom wait methods to enhance reliability.
- **Property-Driven Configuration**: Dynamic platform selection and configurations via `config.properties`.
- **Extensibility**: Modular `BaseTest` class for easy integration of new test cases.
- **CI/CD Integration**: Designed for seamless integration into continuous testing pipelines.

## Resources

The app being tested in this framework is available on GitHub:  
[Hangoutz - Event Planner App Repository for iOS](https://github.com/vanjamihajlovic/Hangoutz-iOS)  
[Hangoutz - Event Planner App Repository for Android](https://github.com/mrsicsasa/Hangoutz) 

### Setting Up the App for Testing

1. Clone or download the Event Planner App repository from the link above.
2. Place the app's APK (for Android) or IPA (for iOS) file in the `src/main/resources` folder of this repository.
3. Update the `config.properties` file to point to the app's location

## Technologies Used

- **Programming Language**: Java  
- **Framework**: TestNG  
- **Mobile Automation**: Appium  
- **Dependency Management**: Maven  

## Setup and Usage

### Prerequisites

1. **Java Development Kit (JDK)**: Ensure JDK 11 or higher is installed.  
2. **Appium Server**: Install and start the Appium server.  
3. **Device Configuration**:  
   - **For Android**: Connect a device or emulator.  
   - **For iOS**: Ensure an iOS device or simulator is available, with the necessary certificates installed.  
4. **Dependencies**: Install project dependencies via Maven.

### Code Structure

- **`BaseTest`**: Provides core functionalities, including platform-specific setups, reusable methods, and teardown logic.  
- **Utilities**:  
  - Wait methods for visibility and presence checks.  
  - Input interactions (click, sendKeys, clear, swipe).  
  - Platform-specific actions (e.g., back navigation on Android).  
- **TestNG Annotations**: Manages test initialization and cleanup using `@BeforeMethod` and `@AfterMethod`.

## Test Coverage

The test automation suite covers the following screens and workflows:

1. **Event Screen**: Event creation, editing, deletion, and detailed interactions.  
2. **Friends Screen**: Adding, removing, and managing friends.  
3. **Profile/Settings Screen**: Updating user profiles and app settings.  
4. **Login Screen**: Authentication tests, including validation for incorrect credentials.  
5. **Registration Screen**: New user registration and validation.  
6. **End-to-End Tests**:  
   - Cover full workflows combining multiple screens to ensure feature integration and user flow accuracy.  
   - **Note**: These tests were developed under tight time constraints, completed in a single day before the presentation. While functional, they require further encapsulation and optimization for maintainability. The limited time for these tests was due to significant manual testing efforts conducted beforehand.  

## Authors

- **Nemanja Jovanović** - [GitHub](https://github.com/nemanjagit)
- **Milan Obrenov** - [GitHub](https://github.com/MilanObrenov)

## License

This project does not include a license and is not open for distribution or modification.
