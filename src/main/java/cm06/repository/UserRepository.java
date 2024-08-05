package cm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cm06.config.MySQLConfig;
import cm06.enity.RolesEntity;
import cm06.enity.UserEntity;
import cm06.service.UserService;

public class UserRepository {
	
	public UserEntity getUserById(int id) {
		UserEntity userEntity = null;
		try {
        	Connection connection = MySQLConfig.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
				userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setFirstName(resultSet.getString("first_name"));
				userEntity.setLastName(resultSet.getString("last_name"));
				userEntity.setUserName(resultSet.getString("username"));
				userEntity.setPhone(resultSet.getString("phone"));
				
				RolesEntity rolesEntity = new RolesEntity();
				rolesEntity.setName(resultSet.getString("name"));
				
				userEntity.setRole(rolesEntity);
				}
        
        } catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
        

        return userEntity;
    }
	
	public int updateUser (String id, String first_name, String last_name, String phone) {
    	Connection connection = MySQLConfig.getConnection();
    	String sql = "UPDATE users u SET first_name = ?, last_name = ?, phone = ? WHERE id = ? ";
    	int count = 0;
    	try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, first_name);
			statement.setString(2, last_name);
			statement.setString(3, phone);
			statement.setString(4, id);
			count = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return count;
    }
	
	public void addUser(String password, String first_name, String last_name, String username, String phone, String id_role) {
			String sql = "INSERT INTO users (password, first_name, last_name, username, phone, id_role)\r\n"
					+ "VALUES\r\n"
					+ "(?, ?, ?, ?, ?, ?)";			

			try {
				Connection connection = MySQLConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, password);
				statement.setString(2, first_name);
				statement.setString(3, last_name);
				statement.setString(4, username);
				statement.setString(5, phone);
				statement.setString(6, id_role);
				
				statement.executeUpdate();
				statement.close();
	            
		    } catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		    }	
    }
	
	public int deleteUserById(int id) {
		int rowCount = 0;
		String sql = "DELETE FROM users u WHERE u.id = ?";
		
		try {
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			rowCount = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public List<UserEntity> getUser() {
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		
		String sql = "SELECT u.id, u.first_name, u.last_name, u.username, r.name \r\n"
				+ "FROM users u\r\n"
				+ "JOIN roles r ON u.id_role = r.id ";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setFirstName(resultSet.getString("first_name"));
				userEntity.setLastName(resultSet.getString("last_name"));
				userEntity.setUserName(resultSet.getString("username"));
				
				RolesEntity rolesEntity = new RolesEntity();
				rolesEntity.setName(resultSet.getString("name"));
				
				userEntity.setRole(rolesEntity);
				
				users.add(userEntity);
			}
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		
		return users;
		
	}

}
