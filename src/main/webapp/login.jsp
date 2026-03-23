<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Course Registration | Login</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css">
</head>
<body>
<div class="login-shell">
  <div class="login-layout">
    <section class="glass login-panel">
      <span class="eyebrow">🎓 Smart registration experience</span>
      <h1>Welcome to the <span class="highlight">Student Course Registration System</span></h1>
      <p>Sign in to manage course offerings, track registrations, and keep every academic workflow simple, secure, and beautifully organized.</p>

      <div class="metric-row">
        <div class="metric-box"><strong>Role-based</strong><span class="muted">Admin and student experiences tailored to each user.</span></div>
        <div class="metric-box"><strong>Fast actions</strong><span class="muted">Quick links for course creation, registration, and review.</span></div>
        <div class="metric-box"><strong>JSP + Servlet</strong><span class="muted">A polished UI built on the app's existing Java backend.</span></div>
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
</body>
</html>
