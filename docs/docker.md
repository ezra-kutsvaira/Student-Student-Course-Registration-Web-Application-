# Docker Guide

## Overview
This document explains how to run the application using Docker Compose.

## Prerequisites
Make sure the following are available:

- Docker
- Docker Compose
- An existing MySQL database reachable from the container

## Configuration
Update `docker-compose.yml` with the correct values for:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `DB_SSL`
- `DB_ALLOW_PUBLIC_KEY_RETRIEVAL`
- `DB_SERVER_TIMEZONE`

## Start the Application

```bash
docker compose up --build
```

## Access
The application should be available at:

- `http://localhost:9090`

## Stop the Application

```bash
docker compose down
```

## Notes
The container maps internal port `8080` to host port `9090`.
