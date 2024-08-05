package cm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cm06.config.MySQLConfig;
import cm06.enity.RolesEntity;

public class RoleRepository {
	
	public List<RolesEntity> getRole(){
		
		List<RolesEntity> roles = new ArrayList<RolesEntity>();
		
		String sql = "SELECT * FROM roles";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				RolesEntity rolesEntity = new RolesEntity();
				rolesEntity.setId(resultSet.getInt("id"));
				rolesEntity.setName(resultSet.getString("name"));
				rolesEntity.setDescription(resultSet.getString("description"));
				
				roles.add(rolesEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return roles;
	}

}
