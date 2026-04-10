<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String assetVersion = "20260323";
String username = (String) session.getAttribute("username");
String role = (String) session.getAttribute("role");
if (username == null || role == null || !"STUDENT".equalsIgnoreCase(role)) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Dashboard</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css?v=<%= assetVersion %>">
</head>
<body>
<div class="container">
  <nav class="glass navbar">
    <div class="brand">
      <div class="brand-badge">🎓</div>
      <div>
        <h2>Student Dashboard</h2>
        <p>Hello, <strong><%= username %></strong>. You can review and manage your semester registrations here.</p>
      </div>
    </div>
    <div class="nav-actions">
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/AvailableCoursesServlet">Browse courses</a>
      <a class="btn btn-danger" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
    </div>
  </nav>

  <section class="hero">
    <div class="glass hero-card">
      <span class="eyebrow">Student Registration Center</span>
      <h1>Register for courses with <span class="highlight">clarity and confidence</span></h1>
      <p>Review available courses, submit registrations, and verify your enrolled courses through a clear and organized student workflow.</p>
      <div class="actions-inline" style="margin-top: 20px;">
        <a class="btn btn-primary" href="<%= request.getContextPath() %>/AvailableCoursesServlet">View available courses</a>
        <a class="btn btn-secondary" href="<%= request.getContextPath() %>/ViewRegisteredCoursesServlet">My registered courses</a>
      </div>
    </div>
    <aside class="glass info-card">
      <h3 style="margin-top:0;">Student actions</h3>
      <div class="card-list">
        <div class="info-item"><div class="info-icon">🔎</div><div><strong>Review offerings</strong><div class="muted">Browse course codes, lecturers, credits, and remaining capacity.</div></div></div>
        <div class="info-item"><div class="info-icon">📝</div><div><strong>Submit registration</strong><div class="muted">Register directly from the course list with immediate feedback.</div></div></div>
        <div class="info-item"><div class="info-icon">✅</div><div><strong>Confirm enrollment</strong><div class="muted">Open your registered course list to verify your current selections.</div></div></div>
      </div>
    </aside>
  </section>

  <section class="grid-3">
    <article class="glass stat">
      <div class="label">Current role</div>
      <div class="value"><%= role %></div>
      <div class="label">Protected student-only experience.</div>
    </article>
    <article class="glass stat">
      <div class="label">Main action</div>
      <div class="value">Register</div>
      <div class="label">Select your preferred course and submit registration.</div>
    </article>
    <article class="glass stat">
      <div class="label">Review progress</div>
      <div class="value">My Courses</div>
      <div class="label">Open your registered course list to confirm enrollment.</div>
    </article>
  </section>
</div>
<script src="<%= request.getContextPath() %>/assets/app.js?v=<%= assetVersion %>"></script>
</body>
</html>
