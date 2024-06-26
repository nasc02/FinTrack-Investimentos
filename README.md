
# FinTrack-Investimentos

FinTrack API is a RESTful web service built with Spring Boot for managing user accounts and investments using DynamoDB as the data store. It provides endpoints for creating, reading, updating, and deleting users and investments.
Features

    User Management:
        Create a new user
        Retrieve user details by ID
        Update user information
        Delete a user

    Investment Management:
        Create a new investment
        Retrieve all investments
        Retrieve investment details by ID
        Update investment details
        Delete an investment

Technologies Used

    Spring Boot: Backend framework for building Java applications.
    Spring Data DynamoDB: Integration with DynamoDB for data persistence.
    JUnit: Unit testing framework.
    AWS DynamoDB: Managed NoSQL database service.
    Java: Programming language for application logic.
    Maven: Dependency management and build automation tool.

Project Structure

The project is structured as follows:


├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── br.fintrack.configs/           # Configs for Dynamo db connection
│   │   │   ├── br.fintrack.controllers/       # Controllers for REST API endpoints
│   │   │   ├── br.fintrack.repositories/      # Spring Data repositories for data access
│   │   │   ├── br.fintrack.models/            # Entity classes representing data models
│   └── test/
│       └── java/
│           └── br.fintrack.tests/             # Unit tests
├── pom.xml                                   # Maven build configuration
└── README.md                                 # Project documentation (you are here rs :) )

API Endpoints
Users

    POST /api/users
        Creates a new user.
    GET /api/users
        Retrieves all users.
    GET /api/users/{id}
        Retrieves a user by ID.
    PUT /api/users/{id}
        Updates a user by ID.
    DELETE /api/users/{id}
        Deletes a user by ID.

Investments

    POST /api/investments
        Creates a new investment.
    GET /api/investments
        Retrieves all investments.
    GET /api/investments/{id}
        Retrieves an investment by ID.
    PUT /api/investments/{id}
        Updates an investment by ID.
    DELETE /api/investments/{id}
        Deletes an investment by ID.

Running the Application

Configuration


Configure AWS credentials and region in a application.properties file in the following way:

amazon.aws.accesskey=your_access_key
amazon.aws.secretkey=your_secret_key

after creating this file you need to set the following env variable: -Dspring.config.location=file:///C:/Users/You/yourLocationToTheFile/applications.properties

make sure to have these tables in the AWS dynamoDB
Investiment
User


Terminal

    mvn spring-boot:run

    The application will start at http://localhost:8080/finTrack-investiment-api/swagger-ui/index.html.

Testing

Unit tests are implemented using JUnit. To run the tests:

  mvn test



