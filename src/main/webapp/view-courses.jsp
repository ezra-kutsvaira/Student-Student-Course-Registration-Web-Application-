<%@ page import="java.util.List,model.Course" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
String role = (String) session.getAttribute("role");
if (username == null || role == null || !"ADMIN".equalsIgnoreCase(role)) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
List<Course> courses = (List<Course>) request.getAttribute("courses");
String message = request.getParameter("message");
int totalCourses = courses == null ? 0 : courses.size();
int totalCapacity = 0;
int totalEnrolled = 0;
if (courses != null) {
    for (Course course : courses) {
        totalCapacity += course.getCapacity();
        totalEnrolled += course.getEnrolledCount();
    }
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Courses</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css">
</head>
<body>
<div class="container">
  <nav class="glass navbar">
    <div class="brand">
      <div class="brand-badge">📋</div>
      <div>
        <h2>Course Management</h2>
        <p>Admin view for adding, updating, and deleting courses.</p>
      </div>
    </div>
    <div class="nav-actions">
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/admin-dashboard.jsp">Back to dashboard</a>
      <a class="btn btn-danger" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
    </div>
  </nav>

  <% if (message != null && !message.trim().isEmpty()) { %>
    <div class="flash success"><%= message %></div>
  <% } %>

  <section class="hero">
    <div class="glass hero-card">
      <span class="eyebrow">🧩 Catalog workspace</span>
      <h1>Manage the full <span class="highlight">course inventory</span></h1>
      <p>Use the form to add a new course and the searchable table below to maintain the catalog. Actions are designed for speed with confirmation safeguards for deletions.</p>
      <div class="grid-3" style="margin-top: 24px;">
        <div class="stat"><div class="label">Courses</div><div class="value"><%= totalCourses %></div></div>
        <div class="stat"><div class="label">Total seats</div><div class="value"><%= totalCapacity %></div></div>
       	<div class="stat"><div class="label">Students enrolled</div><div class="value"><%= totalEnrolled %></div></div>
      </div>
    </div>
    <aside class="glass form-card">
      <h3 style="margin-top:0;">Add a new course</h3>
      <form action="<%= request.getContextPath() %>/AddCourseServlet" method="post">
        <div class="field">
          <label for="courseCode">Course code</label>
          <input class="input" type="text" id="courseCode" name="courseCode" placeholder="CSC401" required>
        </div>
        <div class="field" style="margin-top:14px;">
          <label for="courseName">Course name</label>
          <input class="input" type="text" id="courseName" name="courseName" placeholder="Advanced Web Systems" required>
        </div>
        <div class="field" style="margin-top:14px;">
          <label for="lecturer">Lecturer</label>
          <input class="input" type="text" id="lecturer" name="lecturer" placeholder="Dr. Jane Doe" required>
        </div>
        <div class="form-grid" style="margin-top:14px;">
          <div class="field">
            <label for="credits">Credits</label>
            <input class="input" type="number" id="credits" name="credits" min="1" placeholder="3" required>
          </div>
          <div class="field">
            <label for="capacity">Capacity</label>
            <input class="input" type="number" id="capacity" name="capacity" min="1" placeholder="60" required>
          </div>
        </div>
        <div style="margin-top: 20px;">
          <button class="btn btn-primary" type="submit">Add course</button>
        </div>
      </form>
    </aside>
  </section>

  <section class="glass table-card">
    <div class="toolbar">
      <div>
        <h3 style="margin:0;">All courses</h3>
        <div class="section-subtitle">Search by code, title, or lecturer.</div>
      </div>
      <div class="filters">
        <input class="input" style="min-width: 260px;" type="search" placeholder="Search catalog..." data-filter-target="#courseTableBody">
      </div>
    </div>

    <% if (courses == null || courses.isEmpty()) { %>
      <div class="empty-state">
        <h3>No courses yet</h3>
        <p class="muted">Add your first course using the form above to populate the catalog.</p>
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
              <th>Enrolled</th>
              <th>Slots left</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody id="courseTableBody">
            <% for (Course course : courses) { %>
              <tr data-search-text="<%= course.getCourseCode() %> <%= course.getCourseName() %> <%= course.getLecturer() %>">
                <td><span class="badge info"><%= course.getCourseCode() %></span></td>
                <td><strong><%= course.getCourseName() %></strong></td>
                <td><%= course.getLecturer() %></td>
                <td><%= course.getCredits() %></td>
                <td><%= course.getCapacity() %></td>
                <td><%= course.getEnrolledCount() %></td>
                <td><%= course.getSlotsLeft() %></td>
                <td>
                  <div class="actions-inline">
                    <a class="btn btn-warning" href="<%= request.getContextPath() %>/UpdateCourseServlet?courseId=<%= course.getCourseId() %>">Edit</a>
                    <form class="inline" action="<%= request.getContextPath() %>/DeleteCourseServlet" method="post" data-confirm="Delete <%= course.getCourseCode() %> from the catalog?">
                      <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                      <button class="btn btn-danger" type="submit">Delete</button>
                    </form>
                  </div>
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
