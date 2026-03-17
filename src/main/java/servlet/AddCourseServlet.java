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
 * Admin servlet to create a new course.
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletSecurityUtil.ensureRole(request, response, "ADMIN")) {
            return;
        }

        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String lecturer = request.getParameter("lecturer");

        Integer credits = parsePositiveInt(request.getParameter("credits"));
        Integer capacity = parsePositiveInt(request.getParameter("capacity"));

        if (isBlank(courseCode) || isBlank(courseName) || isBlank(lecturer) || credits == null || capacity == null) {
            redirectWithMessage(response, request, "Course creation failed: fill all fields with valid values.");
            return;
        }

        Course course = new Course(courseCode.trim(), courseName.trim(), lecturer.trim(), credits, capacity);
        boolean added = courseDAO.addCourse(course);

        if (added) {
            redirectWithMessage(response, request, "Course added successfully.");
        } else {
            redirectWithMessage(response, request, "Unable to add course.");
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
