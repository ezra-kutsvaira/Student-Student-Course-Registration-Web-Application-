package servlet;

import dao.UserDAO;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private final UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Always forward users to the login form when they access this servlet directly via GET
    	 request.getRequestDispatcher("login.jsp").forward(request, response);
    }
   
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Read and trim credentials from the submitted form
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	if(isBlank(username) || isBlank(password)) {
    		// Keep the error in request scope because we are forwarding back to the same request.
            request.setAttribute("errorMessage", "Username and password are required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
    	}
    	
    	//Authentication of the user
    	
    	User user = userDAO.login(username.trim(), password.trim());
    	
    	if (user != null) {
            // Prevent session fixation by invalidating any previous session.
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            
         // Create a fresh session for the authenticated user.
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            
            // Route user based on role.
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp");
            } else if ("STUDENT".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/student-dashboard.jsp");
            } else {
                // Fallback for unexpected role values.
                session.invalidate();
                request.setAttribute("errorMessage", "Unknown user role. Please contact support.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            return;
        }

        // Invalid credentials.
        request.setAttribute("errorMessage", "Invalid username or password.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
            
}
	

