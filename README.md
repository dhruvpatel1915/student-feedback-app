# Student Feedback App

A beginner-friendly Java Servlet project that shows the difference between **doGet()** and **doPost()**.

## Features
- Submit feedback with a form (**POST**, data sent in the request body)
- View and search feedback (**GET**, data sent in the URL)
- Redirect after POST, so refreshing the page does not create duplicate feedback
- HTML escaping to prevent script injection

## Technologies
- Java 11
- Java Servlets (javax.servlet 4.0)
- HTML / CSS
- Maven
- Apache Tomcat 9

## How doGet and doPost are used here

| Action | Method | Servlet method | Where the data goes |
|---|---|---|---|
| Submit feedback | POST | `doPost()` | Request body |
| Search / view feedback | GET | `doGet()` | URL (`?search=Rohit`) |

## Project structure
```
student-feedback-app/
├── pom.xml
├── README.md
└── src/main
    ├── java/com/feedback/FeedbackServlet.java
    └── webapp/index.html
```

## How to run
1. Install JDK 11+, Maven and Apache Tomcat 9.
2. Build the project:
   ```
   mvn clean package
   ```
3. Copy `target/student-feedback-app-1.0.war` to Tomcat's `webapps` folder.
4. Start Tomcat and open:
   ```
   http://localhost:8080/student-feedback-app-1.0/
   ```

> Using Tomcat 10 or later? Replace `javax.servlet` with `jakarta.servlet` in the code and in `pom.xml`.

## Things to try
- Submit feedback, then look at the address bar. Nothing is visible (POST).
- Search for a name and look at the address bar: `feedback?search=...` (GET).
- Press Refresh after submitting. No duplicate is created.

## Future improvements
- Store feedback in a MySQL database using JDBC
- Add a delete option for the admin (using POST, never GET)
- Add a login page
- Improve the design with Bootstrap

## Authors
Your Name | MSc IT
