<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String assetVersion = "20260323";
String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Course Registration | Login</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css?v=<%= assetVersion %>">
</head>
<body>
<div class="login-shell">
  <div class="login-layout">
    <section class="glass login-panel">
      <span class="eyebrow">Academic Registration Portal</span>
      <h1>Welcome to the <span class="highlight">Student Course Registration System</span></h1>
      <p>Sign in to access secure, role-specific tools for course management, enrollment, and academic record review.</p>

      <div class="metric-row">
        <div class="metric-box"><strong>Role-Based Access</strong><span class="muted">Separate interfaces for administrators and students with appropriate permissions.</span></div>
        <div class="metric-box"><strong>Efficient Workflow</strong><span class="muted">Direct navigation for course creation, enrollment, and progress tracking.</span></div>
        <div class="metric-box"><strong>Reliable Platform</strong><span class="muted">Professional JSP interface integrated with the existing Java Servlet backend.</span></div>
      </div>
    </section>

    <section class="glass login-panel">
      <div class="brand" style="margin-bottom: 20px;">
        <div class="brand-badge">📘</div>
        <div>
          <h2>Secure Sign In</h2>
          <p>Enter your username and password to continue.</p>
        </div>
      </div>

      <% if (errorMessage != null) { %>
        <div class="flash error"><strong>Login failed:</strong> <%= errorMessage %></div>
      <% } %>

      <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
        <div class="field">
          <label for="username">Username</label>
          <input class="input" type="text" id="username" name="username" placeholder="e.g. admin01 or student01" required>
        </div>
        <div class="field" style="margin-top: 16px;">
          <label for="password">Password</label>
          <input class="input" type="password" id="password" name="password" placeholder="Enter your password" required>
          <small>Your session will be refreshed after successful login for extra security.</small>
        </div>
        <div style="margin-top: 24px; display: flex; gap: 12px; flex-wrap: wrap;">
          <button class="btn btn-primary" type="submit">Login to dashboard</button>
          <a class="btn btn-secondary" href="<%= request.getContextPath() %>/login.jsp">Reset form</a>
        </div>
      </form>
    </section>
  </div>
</div>
<script src="<%= request.getContextPath() %>/assets/app.js?v=<%= assetVersion %>"></script>
</body>
</html>
