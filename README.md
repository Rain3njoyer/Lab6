

# Lab - DevOps Employee Database System

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Workflow Status](https://github.com/Rain3njoyer/Lab6/actions/workflows/main.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-11+-orange)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
[![codecov](https://codecov.io/github/Rain3njoyer/Lab6/graph/badge.svg?token=QUVKHJ7T9S)](https://codecov.io/github/Rain3njoyer/Lab6)
![GitHub Release](https://img.shields.io/github/v/release/Rain3njoyer/Lab6)

A DevOps project implementing an employee database management system with automated testing, continuous integration, and Docker containerization. This project covers Labs 3-9 of the DevOps module.

## 📋 Table of Contents
- [About the Project](#about-the-project)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Lab Progress](#lab-progress)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🎯 About the Project

This project is part of a college DevOps course covering essential software engineering practices including:
- Version control with Git
- Continuous Integration/Continuous Deployment (CI/CD)
- Docker containerization
- Automated testing (Unit & Integration tests)
- Database integration
- Bug tracking and project management

The application connects to a MySQL database containing employee records and provides various reporting capabilities for HR management.

## ✨ Features

- **Employee Management**
  - Retrieve employee information by ID, name, or department
  - Display employee salaries and titles
  - Add new employees to the database

- **Department Operations**
  - Get department information
  - List employees by department
  - View department managers

- **Salary Reports**
  - Generate salary reports by department
  - Filter salaries by job title
  - Display salary information for specific roles

- **Automated Testing**
  - Unit tests for core functionality
  - Integration tests with live database
  - Code coverage reporting

- **CI/CD Pipeline**
  - Automated builds with GitHub Actions
  - Docker containerization
  - Automated deployment

## 🛠 Technologies Used

- **Language:** Java 11+
- **Build Tool:** Maven
- **Database:** MySQL 8.0
- **Containerization:** Docker & Docker Compose
- **CI/CD:** GitHub Actions
- **Testing:** JUnit 5
- **Code Coverage:** JaCoCo & Codecov
- **Version Control:** Git & GitHub

## 🚀 Getting Started

### Prerequisites

Before running this project, ensure you have the following installed:

- **Java JDK 11 or higher**
```bash
  java -version
```

- **Maven 3.6+**
```bash
  mvn -version
```

- **Docker & Docker Compose**
```bash
  docker --version
  docker-compose --version
```

- **Git**
```bash
  git --version
```

### Installation

1. **Clone the repository**
```bash
   git clone https://github.com/Rain3njoyer/Lab6.git
   cd Lab6
```

2. **Start the database**
```bash
   docker-compose up -d db
```
   Wait for the database to be ready (check logs: `docker logs -f <container-name>`)

3. **Build the project**
```bash
   mvn clean package
```

4. **Run the application**
   
   **Option 1: Run locally (for debugging)**
```bash
   # Make sure database is running on port 33060
   mvn exec:java
```

   **Option 2: Run with Docker Compose**
```bash
   docker-compose up
```

## 💻 Usage

### Running Locally

The application connects to the MySQL database and performs various operations:
```bash
# Run with default localhost connection
java -jar target/devops.jar

# Run with custom database location
java -jar target/devops.jar db:3306 30000
```

### Running with Docker
```bash
# Start all services (database + application)
docker-compose up

# Run in detached mode
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f
```

### Example Output
```
Connecting to database...
Successfully connected
Emp No     First Name      Last Name            Salary
------     ----------      ---------            ------
10001      Georgi          Facello              60117
10002      Bezalel         Simmel               65828
...
```

## 🧪 Running Tests

### Unit Tests
```bash
# Run all unit tests
mvn test

# Run specific test class
mvn -Dtest=AppTest test
```

### Integration Tests
```bash
# Start database first
docker build -t database ./db
docker run --name employees -dp 33060:3306 database

# Run integration tests
mvn -Dtest=AppIntegrationTest test

# Cleanup
docker stop employees
docker rm employees
docker image rm database
```

### Code Coverage
```bash
# Generate coverage report
mvn test

# View report
open target/site/jacoco/index.html
```

## 📁 Project Structure
```
Lab6/
├── .github/
│   ├── ISSUE_TEMPLATE/
│   │   └── bug_report.md
│   ├── workflows/
│   │   └── main.yml
│   └── PULL_REQUEST_TEMPLATE.md
├── db/
│   ├── Dockerfile
│   └── employees.sql
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/napier/devops/
│   │           ├── App.java
│   │           ├── Employee.java
│   │           └── Department.java
│   └── test/
│       └── java/
│           └── com/napier/devops/
│               ├── AppTest.java
│               └── AppIntegrationTest.java
├── target/
├── .gitignore
├── CODE_OF_CONDUCT.md
├── CONTRIBUTING.md
├── docker-compose.yml
├── Dockerfile
├── LICENSE
├── pom.xml
├── README.md
└── SECURITY.md
```

## 📊 Lab Progress

| Lab | Topic | Status |
|-----|-------|--------|
| Lab 3 | Version Control & Git | ✅ Complete |
| Lab 4 | GitHub & Scrum Setup | ✅ Complete |
| Lab 5 | UML & Class Diagrams | ✅ Complete |
| Lab 6 | Unit Testing | ✅ Complete |
| Lab 7 | Integration Testing | ✅ Complete |
| Lab 8 | Deployment & Releases | ✅ Complete |
| Lab 9 | Bug Tracking | ✅ Complete |

## 🤝 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📧 Contact

**Your Name** - Subhan Sohail

Project Link: [https://github.com/Rain3njoyer/Lab6](https://github.com/Rain3njoyer/Lab6)

## 🙏 Acknowledgments

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Docker Documentation](https://docs.docker.com/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [MySQL Employee Sample Database](https://dev.mysql.com/doc/employee/en/)

---

**Note:** This is an educational project for learning DevOps practices and software engineering principles.
