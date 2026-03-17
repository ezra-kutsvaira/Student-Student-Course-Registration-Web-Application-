package servlet;

import dao.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Admin servlet for deleting a course.
 */
@WebServlet("/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletSecurityUtil.ensureRole(request, response, "ADMIN")) {
            return;
        }

        Integer courseId = parsePositiveInt(request.getParameter("courseId"));
        if (courseId == null) {
            redirectWithMessage(response, request, "Invalid course id.");
            return;
        }

        boolean deleted = courseDAO.deleteCourse(courseId);
        if (deleted) {
            redirectWithMessage(response, request, "Course deleted successfully.");
        } else {
            redirectWithMessage(response, request, "Unable to delete course.");
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
}
