package dao;

import model.Course;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;																						

public class RegistrationDAO {
	
	private static final String INSERT_REGISTRATION_SQL = "INSERT INTO registrations (user_id, course_id) VALUES (?, ?)";
    private static final String CHECK_REGISTRATION_SQL = "SELECT 1 FROM registrations WHERE user_id = ? AND course_id = ? LIMIT 1";
    private static final String SELECT_REGISTERED_COURSES_SQL = "SELECT c.course_id, c.course_code, c.course_name, c.lecturer, c.credits, c.capacity, COUNT(all_r.registration_id) AS enrolled_count " +
    "FROM courses c INNER JOIN registrations r ON c.course_id = r.course_id LEFT JOIN registrations all_r ON c.course_id = all_r.course_id WHERE r.user_id = ? GROUP BY c.course_id, c.course_code, c.course_name, c.lecturer, c.credits, c.capacity ORDER BY c.course_code";
    private static final String SELECT_REGISTRATION_COUNT_SQL = "SELECT COUNT(*) AS enrolled_count FROM registrations WHERE course_id = ?";
    
    public boolean registerCourse(int userId, int courseId) {
    	if(isAlreadyRegistered(userId, courseId) | isCourseFull(courseId)) {
    		return false;
    	}
    	
    	try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REGISTRATION_SQL)){
    				
    		preparedStatement.setInt(1, userId);
    		preparedStatement.setInt(2, courseId);
    		return preparedStatement.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    
   public boolean isAlreadyRegistered(int userId, int courseId) {
	      try (Connection connection = DBConnection.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_REGISTRATION_SQL)) {

	       preparedStatement.setInt(1, userId);
	       preparedStatement.setInt(2, courseId);

	       try (ResultSet resultSet = preparedStatement.executeQuery()) {
	           return resultSet.next();
	       }
	   } catch (SQLException e) {
	       e.printStackTrace();
	       return false;
	   }
	}
   
   public boolean isCourseFull(int courseId) {
       CourseDAO courseDAO = new CourseDAO();
       Course course = courseDAO.getCourseById(courseId);
       if (course == null) {
           return true;
       }
       return getEnrollmentCount(courseId) >= course.getCapacity();
   }

   public int getEnrollmentCount(int courseId) {
       try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGISTRATION_COUNT_SQL)) {

           preparedStatement.setInt(1, courseId);
           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if (resultSet.next()) {
                   return resultSet.getInt("enrolled_count");
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return 0;
   }
   
   
   //get registeredCoursesByStudent
   public List<Course> getRegisteredCourseByStudent(int userId){
	   List <Course> courses = new ArrayList<>();
	   
	   try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGISTERED_COURSES_SQL)) {
		   preparedStatement.setInt(1, userId);

           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
                   Course course = new Course(
                           resultSet.getInt("course_id"),
                           resultSet.getString("course_code"),
                           resultSet.getString("course_name"),
                           resultSet.getString("lecturer"),
                           resultSet.getInt("credits"),
                           resultSet.getInt("capacity"),
                           resultSet.getInt("enrolled_count")
                   );
                   courses.add(course);
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return courses;
   }
	
   }

