# Database Documentation

## Overview
This document describes the database requirements for the Student Course Registration Web Application.

## Required Tables
The application expects these tables to exist:

- `users`
- `courses`
- `registrations`

## Table Purpose

### users
Stores:
- login credentials,
- user role,
- user identity information.

### courses
Stores:
- course details,
- available course records.

### registrations
Stores:
- associations between students and courses.

## Relationship Notes
- A student can register for multiple courses.
- A course can have multiple student registrations.
- The `registrations` table links users and courses.
- The combination of `user_id + course_id` should be unique.

## Maintenance Notes
If the schema changes, update this document together with the code.
