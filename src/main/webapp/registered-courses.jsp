<%@ page import="java.util.List,model.Course" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String assetVersion = "20260323";
String username = (String) session.getAttribute("username");
String role = (String) session.getAttribute("role");
if (username == null || role == null || !"STUDENT".equalsIgnoreCase(role)) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
List<Course> courses = (List<Course>) request.getAttribute("courses");
int totalCredits = 0;
if (courses != null) {
    for (Course course : courses) {
        totalCredits += course.getCredits();
    }
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registered Courses</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css?v=<%= assetVersion %>">
</head>
<body>
<div class="container">
  <nav class="glass navbar">
    <div class="brand">
      <div class="brand-badge">✅</div>
      <div>
        <h2>My Registered Courses</h2>
        <p>Review your enrolled course list and total workload.</p>
      </div>
    </div>
    <div class="nav-actions">
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/AvailableCoursesServlet">Browse more courses</a>
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/student-dashboard.jsp">Dashboard</a>
      <a class="btn btn-danger" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
    </div>
  </nav>

  <section class="grid-2" style="margin-bottom: 24px;">
    <article class="glass stat">
      <div class="label">Registered courses</div>
      <div class="value"><%= courses == null ? 0 : courses.size() %></div>
      <div class="label">Your current enrolled course count.</div>
    </article>
    <article class="glass stat">
      <div class="label">Total credits</div>
      <div class="value"><%= totalCredits %></div>
      <div class="label">Estimated academic load based on your selected courses.</div>
    </article>
  </section>

  <section class="glass table-card">
    <div class="toolbar">
      <div>
        <h3 style="margin:0;">Enrollment summary</h3>
        <div class="section-subtitle">Search within your enrolled courses.</div>
      </div>
      <div class="filters">
        <input class="input" style="min-width: 260px;" type="search" placeholder="Search my courses..." data-filter-target="#registeredCourseBody">
      </div>
    </div>

    <% if (courses == null || courses.isEmpty()) { %>
      <div class="empty-state">
        <h3>You have not registered any courses yet</h3>
        <p class="muted">Visit the available courses page to begin building your schedule.</p>
        <a class="btn btn-primary" href="<%= request.getContextPath() %>/AvailableCoursesServlet">Find available courses</a>
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
            </tr>
          </thead>
          <tbody id="registeredCourseBody">
            <% for (Course course : courses) { %>
              <tr data-search-text="<%= course.getCourseCode() %> <%= course.getCourseName() %> <%= course.getLecturer() %>">
                <td><span class="badge info"><%= course.getCourseCode() %></span></td>
                <td><strong><%= course.getCourseName() %></strong></td>
                <td><%= course.getLecturer() %></td>
                <td><%= course.getCredits() %></td>
                <td><%= course.getCapacity() %></td>
              </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    <% } %>
  </section>
</div>
<script src="<%= request.getContextPath() %>/assets/app.js?v=<%= assetVersion %>"></script>
</body>
</html>
