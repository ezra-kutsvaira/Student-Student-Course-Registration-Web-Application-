package dao;
import model.Course;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
	
	 private static final String INSERT_COURSE_SQL = "INSERT INTO courses (course_code, course_name, lecturer, credits, capacity) VALUES (?, ?, ?, ?, ?)";
     private static final String UPDATE_COURSE_SQL = "UPDATE courses SET course_code = ?, course_name = ?, lecturer = ?, credits = ?, capacity = ? WHERE course_id = ?";
     private static final String DELETE_COURSE_SQL = "DELETE FROM courses WHERE course_id = ?";
     private static final String SELECT_ALL_COURSES_SQL = "SELECT course_id, course_code, course_name, lecturer, credits, capacity FROM courses";
     private static final String SELECT_COURSE_BY_ID_SQL = "SELECT course_id, course_code, course_name, lecturer, credits, capacity FROM courses WHERE course_id = ?";
     
    
     //add Course
     public boolean addCourse(Course course) {
    	 try (Connection connection = DBConnection.getConnection();
    	      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE_SQL)) {
    	 
    	 	  	 preparedStatement.setString(1, course.getCourseCode());
    			 preparedStatement.setString(2, course.getCourseName());
    			 preparedStatement.setString(3, course.getLecturer());
    			 preparedStatement.setInt(4, course.getCredits());
    			 preparedStatement.setInt(5, course.getCapacity());
    			 
    			 return preparedStatement.executeUpdate() > 0;
             } catch (SQLException e) {
            	 e.printStackTrace();
            	 return false;	 
             } 			 
     }
     
     //Update Course
       public boolean updateCourse(Course course) {
         try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_SQL)) {

                preparedStatement.setString(1, course.getCourseCode());
                preparedStatement.setString(2, course.getCourseName());
                preparedStatement.setString(3, course.getLecturer());
                preparedStatement.setInt(4, course.getCredits());
                preparedStatement.setInt(5, course.getCapacity());
                preparedStatement.setInt(6, course.getCourseId());

                return preparedStatement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;     
            }
       }
       
       
       //delete course
       public boolean deleteCourse(int courseId) {
           try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE_SQL)) {

               preparedStatement.setInt(1, courseId);
               return preparedStatement.executeUpdate() > 0;
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }
       
       //List all courses
       public List<Course> getAllCourses() {
           List<Course> courses = new ArrayList<>();

           try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES_SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

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
           } catch (SQLException e) {
               e.printStackTrace();
           }

           return courses;
       }
       
       //Get course by id
       public Course getCourseById(int courseId) {
           try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE_BY_ID_SQL)) {

               preparedStatement.setInt(1, courseId);

               try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   if (resultSet.next()) {
                       return new Course(
                               resultSet.getInt("course_id"),
                               resultSet.getString("course_code"),
                               resultSet.getString("course_name"),
                               resultSet.getString("lecturer"),
                               resultSet.getInt("credits"),
                               resultSet.getInt("capacity")
                       );
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }

           return null;
       }
       
}
