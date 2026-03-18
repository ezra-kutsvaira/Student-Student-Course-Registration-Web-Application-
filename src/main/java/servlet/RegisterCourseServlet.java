package servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;



import dao.RegistrationDAO;

@WebServlet("/RegisterCourseServlet")

public class RegisterCourseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final RegistrationDAO registrationDAO = new RegistrationDAO();
	
	//ensures that the user is student
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		if(!ServletSecurityUtil.ensureRole(request, response, "STUDENT")) {
			return;
		}
		
		Integer userId = ServletSecurityUtil.getLoggedInUserId(request);
		Integer courseId = parsePositiveInt(request.getParameter("courseId"));
		
		//Find all fields
		if(userId == null || courseId == null) {
			redirectWithMessage(response, request, "Invalid registration request.");
			return;
		}
		
		//Check for duplication registration
		if(registrationDAO.isAlreadyRegistered(userId, courseId)) {
			redirectWithMessage(response, request, "You are already registered in this course");
			return;
		}
		
		//this is true
		boolean registered = registrationDAO.registerCourse(userId, courseId);
		if(registered) {
			redirectWithMessage(response, request, "Course registered successfully.");
		}else {
			redirectWithMessage (response, request, "Unable to register course");
		}
		
	}
	
	//Helper Methods
	private void redirectWithMessage(HttpServletResponse response, HttpServletRequest request, String message) throws IOException {
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.name());
        response.sendRedirect(request.getContextPath() + "/AvailableCoursesServlet?message=" + encodedMessage);
    }
	
	private Integer parsePositiveInt(String input) {
		try {
			int value = Integer.parseInt(input);
			return value > 0 ? value :null;
		}catch (NumberFormatException ex) {
		return null;
		}
	}

}
