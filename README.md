# Fitness Club Management System

## Project Description

This project is a JavaFX application designed to help a fitness club manage their memberships and class schedules. 
The software provides an intuitive graphical user interface (GUI) that allows staff to add and remove members, manage the attendance of fitness classes, generate billing statements, and display membership information in a specified order.

## Features

- Membership management: Allows staff to add and remove members.
- Class schedule management: Manages the attendance of fitness classes.
- Billing: Generates billing statements for members.
- Display: Shows membership information in a specified order.
- GUI: Provides an intuitive graphical user interface for easy navigation and operation.

## Installation

1. Download the zipped project folder.
2. Unzip the project folder.
3. Open the project in your Java IDE.

## Project Structure

The project includes the following main components:

- `Member` class: This is an abstract class that defines the common instance variables and instance methods of the subclasses. It includes a `Profile` object, a `Date` object for the expiration date, and a `Location` object for the home studio.
- `Basic`, `Family`, and `Premium` classes: These classes extend the `Member` class. Each of these classes represents a different type of membership and includes specific instance variables and methods relevant to that type of membership.
- `MemberList` class: This class is an array-based implementation of a linear data structure to hold a list of member objects. It manages the list of members, allowing for adding and removing members.
- `Profile` class: This class includes instance variables for the first name, last name, and date of birth of a member. It provides methods for comparing and representing profiles.
- `StudioManagerMain` class: This class contains the `main()` method with `launch()` method to run your project.
- `StudioManagerController` class: This class contains the event handlers for the GUI.
- `studioManagerView.fxml`: This is the GUI design file.
  
## Testing

The application has been thoroughly tested both manually and using JUnit tests. Manual testing involves interacting with the application's user interface, clicking through the various functionalities, and ensuring that it behaves as expected. JUnit tests are used to automatically test the application's logic and ensure the correctness of its functions.

