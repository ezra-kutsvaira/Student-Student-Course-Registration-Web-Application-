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

---

## 🧰 Tech Stack

- ☕ Java Servlets
- 🖥️ JSP
- 🔌 JDBC
- 🐬 MySQL
- 🌐 Apache Tomcat
- 🐳 Docker / Docker Compose *(optional)*

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

- `users`
- `courses`
- `registrations`

### Table Purpose
- `users` 👤: stores credentials and role
- `courses` 📘: stores course details
- `registrations` 🔗: maps students to courses (unique `user_id + course_id`)

---

## ⚡ Quick Start

1. 🏗️ Create the database and tables (`users`, `courses`, `registrations`).
2. 🌱 Add sample users and courses.
3. 🔧 Configure JDBC connection in `DBConnection`.
4. 📦 Build and deploy the app to Tomcat.
5. 🌍 Open the app and log in as admin or student.

---

## 🎯 Suggested Core Scope

This project is intentionally kept small and focused for academic requirements:

- ✅ Authentication + session management
- ✅ Admin course CRUD
- ✅ Student course registration
- ✅ JDBC CRUD operations with `PreparedStatement`

---

## 📝 Notes

- For demonstration projects, plaintext passwords may be used.
- 🔒 In production, passwords should always be hashed.

---

## 📄 License

