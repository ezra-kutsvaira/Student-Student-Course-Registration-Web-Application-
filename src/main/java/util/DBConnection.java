package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private static final String DB_HOST = getEnv("DB_HOST", "localhost");
  private static final String DB_PORT = getEnv("DB_PORT", "3306");
  private static final String DB_NAME = getEnv("DB_NAME", "student_registration");
  private static final String DB_USER = getEnv("DB_USER", "root");
  private static final String DB_PASSWORD = getEnv("DB_PASSWORD", "anotidae15");
  private static final String DB_SSL = getEnv("DB_SSL", "false");
  private static final String DB_ALLOW_PUBLIC_KEY_RETRIEVAL = getEnv("DB_ALLOW_PUBLIC_KEY_RETRIEVAL", "true");
  private static final String DB_SERVER_TIMEZONE = getEnv("DB_SERVER_TIMEZONE", "UTC");

  private static final String JDBC_URL = String.format(
      "jdbc:mysql://%s:%s/%s?useSSL=%s&allowPublicKeyRetrieval=%s&serverTimezone=%s",
      DB_HOST,
      DB_PORT,
      DB_NAME,
      DB_SSL,
      DB_ALLOW_PUBLIC_KEY_RETRIEVAL,
      DB_SERVER_TIMEZONE
  );

  static {
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
          throw new ExceptionInInitializerError("MySQL JDBC driver not found: " + e.getMessage());
      }
  }

  private DBConnection() {
  }

  public static Connection getConnection() throws SQLException {
      Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
      System.out.println("Connected to Database Successfully");
      return connection;
  }

  private static String getEnv(String key, String defaultValue) {
      String value = System.getenv(key);
      return value == null || value.isBlank() ? defaultValue : value;
  }
}
