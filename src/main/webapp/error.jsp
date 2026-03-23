<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String assetVersion = "20260323"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Access Denied</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css?v=<%= assetVersion %>">
</head>
<body>
<div class="login-shell">
  <section class="glass login-panel" style="max-width: 640px;">
    <div class="brand" style="margin-bottom: 18px;">
      <div class="brand-badge">🚫</div>
      <div>
        <h2>Access Denied</h2>
        <p>You do not have permission to view this page.</p>
      </div>
    </div>
    <p class="muted">The system blocked this request because your current session does not match the required role for the page you tried to access.</p>
    <div class="actions-inline" style="margin-top: 24px;">
      <a class="btn btn-primary" href="<%= request.getContextPath() %>/login.jsp">Return to login</a>
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/LogoutServlet">Sign out</a>
    </div>
  </section>
</div>
</body>
</html>
