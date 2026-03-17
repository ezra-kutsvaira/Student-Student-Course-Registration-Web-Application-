package servlet;

import dao.RegistrationDAO;
import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Student servlet that shows only courses already registered by the logged-in student.
 */
@WebServlet("/ViewRegisteredCoursesServlet")
public class ViewRegisteredCoursesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletSecurityUtil.ensureRole(request, response, "STUDENT")) {
            return;
        }

        Integer userId = ServletSecurityUtil.getLoggedInUserId(request);
        List<Course> courses = userId == null
                ? Collections.<Course>emptyList()
                : registrationDAO.getRegisteredCourseByStudent(userId);

        request.setAttribute("courses", courses);
        request.getRequestDispatcher("registered-courses.jsp").forward(request, response);
    }
}
