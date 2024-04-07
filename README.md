# Lottery Web Application README

## Introduction

Welcome to the Lottery Web Application README. This document serves as a guide for developers to set up, understand, and extend the functionality of the Lottery Web Application prototype. The application is intended to facilitate a lottery scheme to raise funds for local charities. As a developer, you will find instructions for setting up the development environment, understanding the project structure, and implementing various tasks to enhance both functionality and security features.

## Development Environment Setup

Before diving into the development tasks, ensure you have the following components set up:

1. **Java Development Kit (JDK)**: Install JDK suitable for your operating system.
2. **IntelliJ IDEA**: This is the recommended IDE for Java development. Import the provided project into IntelliJ IDEA.
3. **Docker**: Install Docker to run the application in containers.
4. **Apache Tomcat Server**: The application utilizes Apache Tomcat Server.
5. **MySQL Database**: Ensure MySQL Database is accessible for storing user data.

## Task 0 - Initial Task

1. Open the Java project in IntelliJ IDEA and familiarize yourself with its structure and code.
2. Review the provided README and associated files.
3. Set up and test the web application using one of the recommended development setups:
   - Full Docker
   - Hybrid
   - Full Local

## Task 1 - Data Input

### Redesign Home Page (index.jsp)

- Create or extend a registration form for new users.
  - Collect first name, last name, email, phone number, username, and password.
  - Implement client-side input validation using JavaScript or JQuery.
  - Ensure valid email format and phone number format.
  - Define password criteria: 8-15 characters, 1 uppercase, 1 lowercase, 1 digit.
- Update the login form for existing users.
  - Implement client-side input validation.
  - Modify the UserLogin servlet to verify login credentials.

### Server-Side Filter (Servlet Filter)

- Create a filter to sanitize data submitted via the registration form.
- Check for forbidden characters and keywords.
- Display appropriate messages based on the filter results.

## Task 2 - Data Creation & Storage

1. Extend the CreateAccount servlet to hash user passwords and update the database schema accordingly.
2. Update the accounts.jsp page to display user information and allow users to enter lottery numbers.
3. Implement AddUserNumbers and GetUserNumbers servlets to handle user lottery number submissions and retrieval.
4. Enhance the accounts.jsp page to display user lottery numbers.

## Task 3 - Data Access

1. Implement login attempt limiting to 3 attempts.
2. Create an admin home page (admin_home.jsp) for viewing user account details.
3. Implement Role-Based Access Control (RBAC) with roles for admin and public users.
4. Generate random winning lottery numbers and store them appropriately.
5. Enable users to check their lottery numbers against the winning numbers.
6. Ensure proper access modifiers are used in the Java code.

## Task 4 - Finishing Touches

1. Modify the accounts.jsp page to remove user lottery numbers after a winning draw.
2. Extend the application to clean up user files when the application is closed.
3. Refactor and tidy up code structure.
4. Add comments to enhance code readability.
5. Remove redundant code.
6. Perform thorough testing of all implemented functionality.
7. Prepare the web application for Docker deployment according to the provided instructions in the docker-compose.yml file.


