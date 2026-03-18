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


@WebServlet("/ViewCourseServlet")
public class ViewCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final CourseDAO courseDAO = new CourseDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(!ServletSecurityUtil.ensureRole(request, response, "ADMIN")) {
    		return;
    	}
    	
    	List<Course> courses = courseDAO.getAllCourses();
    	request.setAttribute("courses", courses);
    	request.getRequestDispatcher("view-courses.jsp").forward(request, response);
    }

}
