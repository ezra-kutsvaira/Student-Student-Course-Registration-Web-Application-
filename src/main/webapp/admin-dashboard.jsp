<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
String role = (String) session.getAttribute("role");
if (username == null || role == null || !"ADMIN".equalsIgnoreCase(role)) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/app.css">
</head>
<body>
<div class="container">
  <nav class="glass navbar">
    <div class="brand">
      <div class="brand-badge">🛠️</div>
      <div>
        <h2>Admin Control Center</h2>
        <p>Welcome back, <strong><%= username %></strong>.</p>
      </div>
    </div>
    <div class="nav-actions">
      <a class="btn btn-secondary" href="<%= request.getContextPath() %>/ViewCoursesServlet">Manage catalog</a>
      <a class="btn btn-danger" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
    </div>
  </nav>

  <section class="hero">
    <div class="glass hero-card">
      <span class="eyebrow">⚡ Admin productivity</span>
      <h1>Build and maintain a <span class="highlight">high-quality course catalog</span></h1>
      <p>Create new courses, review every record, and keep student registration opportunities current. This dashboard gives you fast access to the full course management workflow.</p>
      <div class="actions-inline" style="margin-top: 20px;">
        <a class="btn btn-primary" href="<%= request.getContextPath() %>/ViewCoursesServlet">Open course management</a>
        <button class="btn btn-secondary" type="button" data-copy-text="ADMIN: <%= username %>">Copy admin identity</button>
      </div>
    </div>
    <aside class="glass info-card">
      <h3 style="margin-top:0;">Admin quick guide</h3>
      <div class="card-list">
        <div class="info-item"><div class="info-icon">➕</div><div><strong>Add courses</strong><div class="muted">Create new offerings with credits, lecturer, and capacity details.</div></div></div>
        <div class="info-item"><div class="info-icon">✏️</div><div><strong>Edit details</strong><div class="muted">Adjust records quickly when schedules or staffing change.</div></div></div>
        <div class="info-item"><div class="info-icon">🗑️</div><div><strong>Clean the catalog</strong><div class="muted">Remove outdated courses with a safe confirmation flow.</div></div></div>
      </div>
    </aside>
  </section>

  <section class="grid-3">
    <article class="glass stat">
      <div class="label">User role</div>
      <div class="value"><%= role %></div>
      <div class="label">Authenticated session with protected admin views.</div>
    </article>
    <article class="glass stat">
      <div class="label">Main workflow</div>
      <div class="value">Course CRUD</div>
      <div class="label">Add, edit, review, and remove course records.</div>
    </article>
    <article class="glass stat">
      <div class="label">Next step</div>
      <div class="value">Open catalog</div>
      <div class="label">Use the management page to perform all catalog actions.</div>
    </article>
  </section>
</div>
<script src="<%= request.getContextPath() %>/assets/app.js"></script>
</body>
</html>
