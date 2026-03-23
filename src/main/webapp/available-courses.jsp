<%@ page import="java.util.List,model.Course" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
String role = (String) session.getAttribute("role");
if (username == null || role == null || !"STUDENT".equalsIgnoreCase(role)) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
List<Course> courses = (List<Course>) request.getAttribute("courses");
String message = request.getParameter("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Available Courses</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css">
</head>
<body>
<div class="container">
  <nav class="glass navbar">
    <div class="brand">
      <div class="brand-badge">🧭</div>
      <div>
        <h2>Available Courses</h2>
        <p>Find a course that matches your semester goals.</p>
      </div>
    </div>
    <div class="nav-actions">
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/student-dashboard.jsp">Dashboard</a>
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/ViewRegisteredCoursesServlet">My courses</a>
      <a class="btn btn-danger" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
    </div>
  </nav>

  <% if (message != null && !message.trim().isEmpty()) { %>
    <div class="flash success"><%= message %></div>
  <% } %>

  <section class="glass table-card">
    <div class="toolbar">
      <div>
        <h3 style="margin:0;">Explore the course list</h3>
        <div class="section-subtitle">Search before you register to find the right option quickly.</div>
      </div>
      <div class="filters">
        <input class="input" style="min-width: 260px;" type="search" placeholder="Search by code, title, lecturer..." data-filter-target="#availableCourseBody">
      </div>
    </div>

    <% if (courses == null || courses.isEmpty()) { %>
      <div class="empty-state">
        <h3>No courses available</h3>
        <p class="muted">The administrator has not published any course offerings yet.</p>
      </div>
    <% } else { %>
      <div class="table-wrap">
        <table>
          <thead>
            <tr>
              <th>Code</th>
              <th>Course</th>
              <th>Lecturer</th>
              <th>Credits</th>
              <th>Capacity</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody id="availableCourseBody">
            <% for (Course course : courses) { %>
              <tr data-search-text="<%= course.getCourseCode() %> <%= course.getCourseName() %> <%= course.getLecturer() %>">
                <td><span class="badge info"><%= course.getCourseCode() %></span></td>
                <td><strong><%= course.getCourseName() %></strong></td>
                <td><%= course.getLecturer() %></td>
                <td><%= course.getCredits() %></td>
                <td><span class="badge success"><%= course.getCapacity() %> seats</span></td>
                <td>
                  <form class="inline" action="<%= request.getContextPath() %>/RegisterCourseServlet" method="post" data-confirm="Register for <%= course.getCourseCode() %> - <%= course.getCourseName() %>?">
                    <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                    <button class="btn btn-success" type="submit">Register</button>
                  </form>
                </td>
              </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    <% } %>
  </section>
</div>
<script src="<%= request.getContextPath() %>/assets/app.js"></script>
</body>
</html>
