package servlet;

import dao.RegistrationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Student servlet for registering into a course.
 */
@WebServlet("/RegisterCourseServlet")
public class RegisterCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletSecurityUtil.ensureRole(request, response, "STUDENT")) {
            return;
        }

        Integer userId = ServletSecurityUtil.getLoggedInUserId(request);
        Integer courseId = parsePositiveInt(request.getParameter("courseId"));

        if (userId == null || courseId == null) {
            redirectWithMessage(response, request, "Invalid registration request.");
            return;
        }

        if (registrationDAO.isAlreadyRegistered(userId, courseId)) {
            redirectWithMessage(response, request, "You are already registered in this course.");
            return;
        }

        boolean registered = registrationDAO.registerCourse(userId, courseId);
        if (registered) {
            redirectWithMessage(response, request, "Course registered successfully.");
        } else {
            redirectWithMessage(response, request, "Unable to register for this course.");
        }
    }

    private void redirectWithMessage(HttpServletResponse response, HttpServletRequest request, String message) throws IOException {
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.name());
        response.sendRedirect(request.getContextPath() + "/AvailableCoursesServlet?message=" + encodedMessage);
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
