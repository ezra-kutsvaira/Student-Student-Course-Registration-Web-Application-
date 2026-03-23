# Troubleshooting

## Overview
This document lists common issues and ways to resolve them.

## Common Issues

### Application does not start
Possible causes:
- build failure,
- missing dependencies,
- Tomcat configuration issue.

Suggested checks:
- verify Java and Maven installation,
- run the Maven build again,
- review server logs.

### Database connection fails
Possible causes:
- incorrect environment variables,
- database server not running,
- network connectivity issue,
- wrong credentials.

Suggested checks:
- verify `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, and `DB_PASSWORD`,
- confirm the database is reachable,
- verify MySQL permissions.

### Docker container cannot connect to database
Possible causes:
- wrong host name,
- database not exposed correctly,
- invalid Docker Compose configuration.

Suggested checks:
- review `docker-compose.yml`,
- confirm `host.docker.internal` works in the environment,
- verify the database is reachable from the container.

### Port conflict
Possible cause:
- another service is already using the selected port.

Suggested check:
- verify that host port `9090` is available.

## Maintenance Rule
Add new troubleshooting entries whenever a recurring issue is identified.
