# Developer Guide

## Overview
This document helps developers understand the project structure and development workflow.

## Technology Stack
The project uses:

- Java Servlets
- JSP
- JDBC
- MySQL
- Apache Tomcat
- Maven
- Docker / Docker Compose

## Project Structure

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

## Package Responsibilities
- `model/`: domain models
- `dao/`: database access logic
- `servlet/`: request handling
- `util/`: shared utility classes

## Development Expectations
- Keep database access in DAO classes.
- Keep request handling in servlets.
- Keep JSP pages focused on presentation.
- Update documentation when features or setup change.

## Related Documents
- `setup.md`
- `database.md`
- `DOCUMENTATION_GUIDE.md`
