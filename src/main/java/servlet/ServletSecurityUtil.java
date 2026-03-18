package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

//Method to support Authorisation and Authentication Checks
public class ServletSecurityUtil {
	
	private ServletSecurityUtil() {
		//prevents the class from instantiation
	}

		public static boolean ensureRole(HttpServletRequest request, HttpServletResponse response, String requiredRole) throws IOException {
			HttpSession session = request.getSession(false);
			
	        if (session == null || session.getAttribute("userId") == null || session.getAttribute("role") == null) {
	            response.sendRedirect(request.getContextPath() + "/login.jsp");
	            return false;
	        	
		}
        
	    //Check Authorization for role
        String role = String.valueOf(session.getAttribute("role"));
        if (!requiredRole.equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return false;
        }

        return true;
    }
	
		public static Integer getLoggedInUserId(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            return null;
	        }

	        Object userIdObj = session.getAttribute("userId");
	        if (userIdObj instanceof Integer) {
	            return (Integer) userIdObj;
	        }

	        return null;
	    }

	
}
