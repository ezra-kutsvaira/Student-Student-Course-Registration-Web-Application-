package servlet;

import dao.CourseDAO;
import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Admin servlet for editing an existing course.
 *
 * GET  -> load one course and show edit page.
 * POST -> persist submitted changes.
 */
@WebServlet("/UpdateCourseServlet")
public class UpdateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletSecurityUtil.ensureRole(request, response, "ADMIN")) {
            return;
        }

        Integer courseId = parsePositiveInt(request.getParameter("courseId"));
        if (courseId == null) {
            redirectWithMessage(response, request, "Invalid course id.");
            return;
        }

        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            redirectWithMessage(response, request, "Course not found.");
            return;
        }

        request.setAttribute("course", course);
        request.getRequestDispatcher("edit-course.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletSecurityUtil.ensureRole(request, response, "ADMIN")) {
            return;
        }

        Integer courseId = parsePositiveInt(request.getParameter("courseId"));
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String lecturer = request.getParameter("lecturer");
        Integer credits = parsePositiveInt(request.getParameter("credits"));
        Integer capacity = parsePositiveInt(request.getParameter("capacity"));

        if (courseId == null || isBlank(courseCode) || isBlank(courseName) || isBlank(lecturer)
                || credits == null || capacity == null) {
            redirectWithMessage(response, request, "Course update failed: invalid input.");
            return;
        }

        Course course = new Course(courseId, courseCode.trim(), courseName.trim(), lecturer.trim(), credits, capacity);
        boolean updated = courseDAO.updateCourse(course);

        if (updated) {
            redirectWithMessage(response, request, "Course updated successfully.");
        } else {
            redirectWithMessage(response, request, "Unable to update course.");
        }
    }

    private void redirectWithMessage(HttpServletResponse response, HttpServletRequest request, String message) throws IOException {
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.name());
        response.sendRedirect(request.getContextPath() + "/ViewCoursesServlet?message=" + encodedMessage);
    }

    private Integer parsePositiveInt(String input) {
        try {
            int value = Integer.parseInt(input);
            return value > 0 ? value : null;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
