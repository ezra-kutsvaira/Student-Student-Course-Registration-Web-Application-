package servlet;
import dao.CourseDAO;
import model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Student servlet that lists all available courses.
 */
@WebServlet("/AvailableCoursesServlet")
public class AvailableCoursesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletSecurityUtil.ensureRole(request, response, "STUDENT")) {
            return;
        }

        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("available-courses.jsp").forward(request, response);
    }
}