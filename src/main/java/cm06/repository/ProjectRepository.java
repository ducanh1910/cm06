package cm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import cm06.config.MySQLConfig;
import cm06.enity.ProjectEntity;


public class ProjectRepository {
	
	public ProjectEntity getProjectById(String id) {
		ProjectEntity projectEntity = null;
		try {
        	Connection connection = MySQLConfig.getConnection();
            String sql = "SELECT * FROM project WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	projectEntity = new ProjectEntity();
            	projectEntity.setId(resultSet.getInt("id"));
				projectEntity.setStartDate(resultSet.getTimestamp("start_date"));
            	projectEntity.setEndDate(resultSet.getTimestamp("end_date"));
				
				}
        
        } catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}

        return projectEntity;
    }
	
	public int updateProject (String id, String name, Timestamp end_date) {
    	Connection connection = MySQLConfig.getConnection();
    	String sql = "UPDATE project SET name = ?, end_date = ? WHERE id = ? ";
    	int count = 0;
    	try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setTimestamp(2, end_date);
			statement.setString(3, id);
			
			count = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return count;
    }
	
	public void addProject(String name, Timestamp start_date, Timestamp end_date) {
	    String sql = "INSERT INTO project (name, start_date, end_date) VALUES (?, ?, ?)";

	    try {
	        Connection connection = MySQLConfig.getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, name);
	        statement.setTimestamp(2, start_date);
	        statement.setTimestamp(3, end_date);

	        statement.executeUpdate();
	        statement.close();

	    } catch (Exception e) {
	        System.out.println("Error : " + e.getMessage());
	    }
	}

	public int deleteProjectById(int id) {
		int rowCount = 0;
		String sql = "DELETE FROM project p WHERE p.id = ?";
		
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
	
	public List<ProjectEntity> getProject() {
		
		List<ProjectEntity> projects = new ArrayList<ProjectEntity>();
		
		String sql = "SELECT * FROM project p ";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ProjectEntity projectEntity = new ProjectEntity();
				projectEntity.setId(resultSet.getInt("id"));
				projectEntity.setName(resultSet.getString("name"));
				projectEntity.setStartDate(resultSet.getTimestamp("start_date"));
				projectEntity.setEndDate(resultSet.getTimestamp("end_date"));
				
				projects.add(projectEntity);
			}
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		
		return projects;
		
	}



}
