package cm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cm06.config.MySQLConfig;
import cm06.enity.StatusEntity;
import cm06.enity.TaskEntity;

public class StatusRepository {
	public List<StatusEntity> getStatus() {
        List<StatusEntity> statuses = new ArrayList<>();
        String sql = "SELECT * FROM status";

        try (Connection connection = MySQLConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
            	StatusEntity status = new StatusEntity();
                status.setId(resultSet.getInt("id"));;
                status.setName(resultSet.getString("name"));
                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

}
