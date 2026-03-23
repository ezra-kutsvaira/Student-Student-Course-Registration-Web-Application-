# Setup Guide

## Overview
This document explains how to set up the Student Course Registration Web Application for local development.

## Prerequisites
Before starting, make sure you have:

- Java installed
- Maven installed
- MySQL available
- Apache Tomcat (if running manually)
- Docker and Docker Compose (optional)

## Environment Variables
The application uses the following database configuration values:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `DB_SSL`
- `DB_ALLOW_PUBLIC_KEY_RETRIEVAL`
- `DB_SERVER_TIMEZONE`

## Local Build
Run:

```bash
mvn clean package
```

## Expected Result
- Maven completes successfully.
- The application artifact is generated.

## Notes
Use this document for local setup details. For Docker-based setup, see `docker.md`.
