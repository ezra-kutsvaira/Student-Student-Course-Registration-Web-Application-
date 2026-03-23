<%@ page import="model.Course" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
String role = (String) session.getAttribute("role");
if (username == null || role == null || !"ADMIN".equalsIgnoreCase(role)) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
Course course = (Course) request.getAttribute("course");
if (course == null) {
    response.sendRedirect(request.getContextPath() + "/ViewCoursesServlet?message=Course+not+found");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Course</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css">
</head>
<body>
<div class="container">
  <nav class="glass navbar">
    <div class="brand">
      <div class="brand-badge">✏️</div>
      <div>
        <h2>Edit Course</h2>
        <p>Update course details for <strong><%= course.getCourseCode() %></strong>.</p>
      </div>
    </div>
    <div class="nav-actions">
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/ViewCoursesServlet">Back to courses</a>
      <a class="btn btn-danger" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
    </div>
  </nav>

  <section class="grid-2">
    <article class="glass info-card">
      <span class="eyebrow">🧠 Editing guidance</span>
      <h2 style="margin-top:0;">Keep course information accurate</h2>
      <p class="muted">Adjust the code, title, lecturer, credits, or available capacity. When you save, the system returns you to the full catalog with a status message.</p>
      <div class="card-list">
        <div class="info-item"><div class="info-icon">1</div><div><strong>Review values</strong><div class="muted">Confirm every field before saving.</div></div></div>
        <div class="info-item"><div class="info-icon">2</div><div><strong>Protect data quality</strong><div class="muted">Credits and capacity accept positive numbers only.</div></div></div>
        <div class="info-item"><div class="info-icon">3</div><div><strong>Save changes</strong><div class="muted">Return to the management page to continue editing other records.</div></div></div>
      </div>
    </article>

    <article class="glass form-card">
      <h3 style="margin-top:0;">Course details</h3>
      <form action="<%= request.getContextPath() %>/UpdateCourseServlet" method="post">
        <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
        <div class="field">
          <label for="courseCode">Course code</label>
          <input class="input" type="text" id="courseCode" name="courseCode" value="<%= course.getCourseCode() %>" required>
        </div>
        <div class="field" style="margin-top:14px;">
          <label for="courseName">Course name</label>
          <input class="input" type="text" id="courseName" name="courseName" value="<%= course.getCourseName() %>" required>
        </div>
        <div class="field" style="margin-top:14px;">
          <label for="lecturer">Lecturer</label>
          <input class="input" type="text" id="lecturer" name="lecturer" value="<%= course.getLecturer() %>" required>
        </div>
        <div class="form-grid" style="margin-top:14px;">
          <div class="field">
            <label for="credits">Credits</label>
            <input class="input" type="number" id="credits" name="credits" min="1" value="<%= course.getCredits() %>" required>
          </div>
          <div class="field">
            <label for="capacity">Capacity</label>
            <input class="input" type="number" id="capacity" name="capacity" min="1" value="<%= course.getCapacity() %>" required>
          </div>
        </div>
        <div style="margin-top: 20px;" class="actions-inline">
          <button class="btn btn-primary" type="submit">Save changes</button>
          <a class="btn btn-secondary" href="<%= request.getContextPath() %>/ViewCoursesServlet">Cancel</a>
        </div>
      </form>
    </article>
  </section>
</div>
<script src="<%= request.getContextPath() %>/assets/app.js"></script>
</body>
</html>
