# 🎓 Student Course Registration Web Application

A Java web application for managing course registration using **Servlets**, **JSP**, **JDBC**, and **MySQL**.

---

## 🚀 Features

- 🔐 **Session-based authentication** (login/logout)
- 👥 **Role-based access** (`ADMIN`, `STUDENT`)
- 🛠️ **Admin can:**
  - ➕ Add courses
  - ✏️ Update courses
  - 🗑️ Delete courses
  - 📋 View all courses
- 🎓 **Student can:**
  - 👀 View available courses
  - 📝 Register for courses
  - 📚 View registered courses
- 🧩 **JDBC integration** with `PreparedStatement` for safer SQL operations
- 🐳 **Dockerized application deployment** with support for an existing MySQL database

---

## 🧰 Tech Stack

- ☕ Java Servlets
- 🖥️ JSP
- 🔌 JDBC
- 🐬 MySQL
- 🌐 Apache Tomcat
- 🐳 Docker / Docker Compose
- 📦 Maven

---

## 📁 Project Structure

```text
src/main/java/
  model/
  dao/
  servlet/
  util/

src/main/webapp/
  login.jsp
  admin-dashboard.jsp
  student-dashboard.jsp
  view-courses.jsp
  available-courses.jsp
  registered-courses.jsp
```

---

## 🗄️ Database Tables

The application expects these tables to already exist in your MySQL database:

- `users`
- `courses`
- `registrations`

### Table Purpose
- `users` 👤: stores credentials and role
- `courses` 📘: stores course details
- `registrations` 🔗: maps students to courses (unique `user_id + course_id`)

---

## ⚡ Quick Start with Docker

### Prerequisites
- Docker
- Docker Compose
- An existing MySQL database reachable from the Docker container

### Configure the database connection

Update the environment variables in `docker-compose.yml` to match your existing database:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `DB_SSL`
- `DB_ALLOW_PUBLIC_KEY_RETRIEVAL`
- `DB_SERVER_TIMEZONE`

> The included compose file uses `host.docker.internal` so the container can connect to a MySQL server running on the Docker host.

### Start the application container

```bash
docker compose up --build
```

The Docker setup publishes container port `8080` on host port `9090` to reduce host-port conflicts.

The application will be available at:
- App: http://localhost:9090

### Stop the container

```bash
docker compose down
```

---

## 🔧 Local non-Docker development

The database connection is configurable through environment variables:

- `DB_HOST` (default: `localhost`)
- `DB_PORT` (default: `3306`)
- `DB_NAME` (default: `student_registration`)
- `DB_USER` (default: `root`)
- `DB_PASSWORD` (default: `anotidae15`)
- `DB_SSL` (default: `false`)
- `DB_ALLOW_PUBLIC_KEY_RETRIEVAL` (default: `true`)
- `DB_SERVER_TIMEZONE` (default: `UTC`)

You can still build the application manually with:

```bash
mvn clean package
```

---

## 📝 Notes

- For demonstration projects, plaintext passwords may be used.
- 🔒 In production, passwords should always be hashed.
- This repository no longer provisions or seeds a MySQL container; it connects to the database you already manage.

---

## 📄 License

This project is for educational use.
