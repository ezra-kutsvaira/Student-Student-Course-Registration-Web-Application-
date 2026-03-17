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
    private static final String SELECT_REGISTERED_COURSES_SQL = "SELECT c.course_id, c.course_code, c.course_name, c.lecturer, c.credits, c.capacity " +
    "FROM courses c INNER JOIN registrations r ON c.course_id = r.course_id WHERE r.user_id = ?";
    
    public boolean registerCourse(int userId, int courseId) {
    	if(isAlreadyRegistered(userId, courseId)) {
    		return false;
    	}
    	
    	try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REGISTRATION_SQL)){
    				
    		preparedStatement.setInt(1, userId);
    		preparedStatement.setInt(1, courseId);
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
                           resultSet.getInt("capacity")
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

