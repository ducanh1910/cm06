 package cm06.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConfig {
	
	//Mở kết nối tới SQL
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			//Khai báo driver sử dụng cho JDBC lên mạng search
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/crmapp", "root", "admin123");
		} catch (ClassNotFoundException e) {
            System.out.println("Không thể tìm thấy driver JDBC: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Không thể kết nối tới CSDL: " + e.getMessage());
            e.printStackTrace();
        }
		
		return connection;
	}

}
