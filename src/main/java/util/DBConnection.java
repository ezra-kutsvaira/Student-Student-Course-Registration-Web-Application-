package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private static final String jdbcUrl = "jdbc:mysql://localhost:3306/student_registration?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
  private static final String dbUser = "root";
  private static final String dbPassword = "anotidae15";
 
  
  //MySQL Driver Loading
  static {
	  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
	  }catch(ClassNotFoundException e) {
		  throw new ExceptionInInitializerError("MySQL JDBC driver not found: " + e.getMessage());
	  }
  }
  
  
  //This prevents creating objects, this is a utility class 
  private DBConnection() {
  }
  
  public static Connection getConnection() throws SQLException {
      Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
      System.out.println("Connected to Database Successfully");
      return connection;
  }
}
