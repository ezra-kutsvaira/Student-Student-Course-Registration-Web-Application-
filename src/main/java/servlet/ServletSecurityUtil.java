package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Small utility methods to keep servlet authentication/authorization checks reusable.
 */
public final class ServletSecurityUtil {

    private ServletSecurityUtil() {
        // Utility class: prevent instantiation.
    }

    public static boolean ensureRole(HttpServletRequest request, HttpServletResponse response, String requiredRole)
            throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null || session.getAttribute("role") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }

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
