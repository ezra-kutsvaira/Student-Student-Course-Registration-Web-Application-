# Documentation Guide

## Purpose
This guide explains how to write, organize, and maintain documentation for the Student Course Registration Web Application.

The goal of the documentation is to help:
- users understand how the system works,
- developers set up and maintain the project,
- contributors update documentation consistently when the project changes.

---

## Audience
This documentation is intended for:
- **Students/End Users** who want to use the application,
- **Developers** who want to run or modify the project,
- **Maintainers/Reviewers** who need to verify documentation quality.

---

## Documentation Principles
All project documentation should follow these principles:

1. **Clarity first**  
   Write in simple, direct language.

2. **Beginner-friendly**  
   Assume the reader may be new to Java web applications, Docker, JSP, Servlets, or MySQL.

3. **Task-oriented**  
   Focus on helping the reader complete a task.

4. **Accurate and current**  
   Documentation must match the current behavior of the project.

5. **Consistent structure**  
   Similar documents should use similar headings and formatting.

6. **Avoid duplication**  
   Do not repeat the same information in multiple places unless necessary.

---

## Documentation Structure
The documentation should be organized as follows:

- `README.md`
  - Project overview
  - Features
  - Tech stack
  - Quick start
  - Basic setup information

- `docs/setup.md`
  - Local development setup
  - Environment variables
  - Build and run instructions

- `docs/docker.md`
  - Docker-specific usage
  - Port mapping
  - Container startup and shutdown

- `docs/database.md`
  - Required tables
  - Table responsibilities
  - Relationships and assumptions

- `docs/user-guide.md`
  - How students use the system
  - How admins use the system

- `docs/developer-guide.md`
  - Code structure
  - Important modules
  - Development workflow

- `docs/troubleshooting.md`
  - Common issues
  - Likely causes
  - Suggested fixes

- `docs/contributing-docs.md`
  - How to update documentation
  - Review expectations
  - Style and formatting rules

---

## What Belongs in Each Document

### README.md
Use the README for:
- project introduction,
- main features,
- technologies used,
- quick-start instructions,
- links to detailed documentation.

Do not put long troubleshooting sections or deep implementation details in the README.

### Setup Documentation
Use setup docs for:
- prerequisites,
- environment configuration,
- build and run commands,
- local development instructions.

### Database Documentation
Use database docs for:
- required tables,
- schema explanation,
- table purpose,
- relationships between entities.

### User Documentation
Use user-guide docs for:
- login instructions,
- student actions,
- admin actions,
- expected workflow through the application.

### Developer Documentation
Use developer docs for:
- code organization,
- package responsibilities,
- servlet/JSP/DAO interactions,
- deployment assumptions.

### Troubleshooting Documentation
Use troubleshooting docs for:
- startup failures,
- database connection errors,
- Tomcat deployment issues,
- environment configuration mistakes.

---

## Writing Standards

### Language
- Use clear, simple English.
- Prefer short sentences.
- Define technical terms the first time they appear.
- Avoid unnecessary jargon.

### Tone
- Be professional and helpful.
- Use instructional language.
- Prefer active voice.

### Headings
Use headings consistently:

- `#` for document title
- `##` for major sections
- `###` for subsections

### Procedures
When describing steps:
- use numbered lists,
- include commands in code blocks,
- state expected results after important steps.

Example:

```bash
mvn clean package
```

Expected result:
- The application builds successfully.
- A deployable artifact is generated.

### Code Blocks
- Use fenced Markdown code blocks.
- Add the language when possible (`bash`, `sql`, `xml`, `java`).

### Lists
- Use bullet points for options or grouped items.
- Use numbered lists for sequences.

### File Names
Use descriptive lowercase file names with hyphens:
- `user-guide.md`
- `developer-guide.md`
- `troubleshooting.md`

---

## Required Sections for Most Documents
Most documentation files should include these sections when relevant:

1. **Overview**
2. **Purpose**
3. **Prerequisites**
4. **Steps / Instructions**
5. **Expected Result**
6. **Troubleshooting**
7. **Related Documents**

---

## Style Rules
Follow these style rules across all documentation:

- Use consistent terminology:
  - “Student”
  - “Admin”
  - “Course”
  - “Registration”
- Use present tense where possible.
- Avoid ambiguous phrases such as “just do this” or “obviously”.
- Do not assume readers know project-specific details.
- Keep paragraphs short.
- Use examples when a process may be confusing.

---

## Screenshots and Diagrams
If screenshots are used:
- only include them when they improve understanding,
- ensure they match the current UI,
- name them clearly,
- place them in a predictable location such as `docs/assets/`.

Example:
- `docs/assets/login-page.png`
- `docs/assets/admin-dashboard.png`
- `docs/assets/student-dashboard.png`

Use diagrams for:
- architecture overview,
- database relationships,
- request flow.

---

## Updating Documentation
Documentation must be updated whenever any of the following change:

- application features,
- user roles or permissions,
- database requirements,
- environment variables,
- Docker configuration,
- deployment steps,
- package structure,
- UI flows that affect user instructions.

If code changes alter behavior, related documentation should be updated in the same change.

---

## Documentation Review Checklist
Before merging documentation updates, verify:

- the document is accurate,
- steps are complete,
- commands are correct,
- formatting is consistent,
- links work,
- screenshots are current,
- duplicated content is minimized,
- the target audience can understand it.

---

## Contributing to Documentation
When adding or updating docs:

1. Identify the correct file to edit.
2. Avoid repeating information already explained elsewhere.
3. Add links to related documentation if needed.
4. Use the existing structure and terminology.
5. Review the final document for clarity and correctness.

---

## Definition of Good Documentation
Good documentation is:

- correct,
- easy to read,
- easy to follow,
- easy to maintain,
- organized by audience and purpose.

If a new contributor can set up, understand, and use the project by following the docs, the documentation is doing its job.
