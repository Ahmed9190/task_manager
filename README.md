# Task Manager API

The Task Manager API is a Spring Boot application that provides a comprehensive task management system with user authentication using JSON Web Tokens (JWT). It allows users to perform various operations related to tasks, categories, and comments.

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Authentication](#authentication)
- [Tasks](#tasks)
- [Categories](#categories)
- [Comments](#comments)

## Features

The Task Manager API offers the following features:

1. **Authentication**: Users can create accounts and authenticate using JWT tokens.
2. **Task Management**: Users can perform CRUD operations on tasks, including creating, updating, deleting, and retrieving tasks.
3. **Category Management**: Users can manage task categories, allowing them to organize tasks effectively.
4. **Comment Management**: Users can add comments to tasks.

## Technologies Used

- Java
- Spring Boot
- Spring Security with JWT
- Spring Data JPA
- Maven for build management
- PostgreSQL
- Docker for database

## Getting Started

1. Clone the repository:

   ```shell
   git clone git@github.com:Ahmed9190/task_manager.git
   cd task_manager
   ```

2. Build and run the application:

   ```shell
   mvn spring-boot:run
   ```

   The API will be available at `http://localhost:8080`.

3. Configure the database settings in `application.yml` as per your requirements.
4. Execute the following commands
   ```shell
   mv src/main/java/com/aw/task_manager/security/constants/SecurityConstants.java.example src/main/java/com/aw/task_manager/security/constants/SecurityConstants.java
   mv src/main/resources/application-secret.yml.example src/main/resources/application-secret.yml
   ```
5. Update the following files with your data
   ```
   src/main/java/com/aw/task_manager/security/constants/SecurityConstants.java
   src/main/resources/application-secret.yml.example src/main/resources/application-secret.yml
   ```
6. (Optional) Use docker to create the database with the following command
   ```shell
   docker compose up
   ```

## Authentication

To use the Task Manager API, you need to authenticate using a bearer token. You can obtain a token by registering and logging in using the following endpoints:

### Register

- **Endpoint:** `POST /auth/register`
- **Request Body:**

```json
{
  "username": "john",
  "email": "john.doe@gmail.com",
  "password": "12345678"
}
```

### Login

- **Endpoint:** `POST /auth/login`
- **Request Body:**

```json
{
  "username": "john",
  "password": "12345678"
}
```

---

## Tasks

The Tasks endpoints allow you to manage tasks.

### All Tasks

- **Endpoint:** `GET /tasks`
- **Parameters:**
  - `title` (optional): Filter tasks by title.

### All Tasks Paginated

- **Endpoint:** `GET /tasks/paginated`
- **Parameters:**
  - `page`: Page number (default is 0).
  - `pageSize`: Number of tasks per page (default is 10).
  - `sortField`: Sort tasks by field (e.g., "dueDate").
  - `sortOrder`: Sort order ("ASC" or "DESC").

### Task by ID

- **Endpoint:** `GET /tasks/{id}`

### Create Task

- **Endpoint:** `POST /tasks`
- **Request Body:**

```json
{
  "title": "Task",
  "description": "Description",
  "dueDate": "2024-01-01T00:00:00",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "categoryIds": [52]
}
```

### Update Task

- **Endpoint:** `PUT /tasks/{id}`
- **Request Body:**

```json
{
  "title": "Task",
  "description": "Description of task",
  "dueDate": "2024-01-01T00:00:00",
  "status": "IN_PROGRESS",
  "priority": "LOW"
}
```

### Delete Task

- **Endpoint:** `DELETE /tasks/{id}`

---

## Categories

The Categories endpoints allow you to manage categories.

### All Categories

- **Endpoint:** `GET /categories`

### Category by ID

- **Endpoint:** `GET /categories/{id}`

### Create Category

- **Endpoint:** `POST /categories`
- **Request Body:**

```json
{
  "name": "Category"
}
```

### Update Category

- **Endpoint:** `PUT /categories/{id}`
- **Request Body:**

```json
{
  "name": "Category 1"
}
```

### Delete Category

- **Endpoint:** `DELETE /categories/{id}`

---

## Comments

The Comments endpoints allow you to manage comments.

### All Comments

- **Endpoint:** `GET /comments`

### Comment by ID

- **Endpoint:** `GET /comments/{id}`

### Create Comment

- **Endpoint:** `POST /comments`
- **Request Body:**

```json
{
  "text": "Great",
  "taskId": 2
}
```

### Update Comment

- **Endpoint:** `PUT /comments/{id}`
- **Request Body:**

```json
{
  "text": "Updated comment text"
}
```

### Delete Comment

- **Endpoint:** `DELETE /comments/{id}`
