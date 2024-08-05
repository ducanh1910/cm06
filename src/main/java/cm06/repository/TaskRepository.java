package cm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cm06.config.MySQLConfig;
import cm06.enity.ProjectEntity;
import cm06.enity.StatusEntity;
import cm06.enity.TaskEntity;
import cm06.enity.UserEntity;

import javax.sql.DataSource;

public class TaskRepository {

    // Lấy tất cả nhiệm vụ
    public List<TaskEntity> getTasks() {
        List<TaskEntity> tasks = new ArrayList<>();
        String query = "SELECT t.id, t.name, t.start_date AS startDate, t.end_date AS endDate, "
                + "u.id AS userId, u.first_name AS FirstName, u.last_name AS LastName, "
                + "p.id AS projectId, p.name AS projectName, "
                + "s.id AS statusId, s.name AS statusName "
                + "FROM task t "
                + "JOIN users u ON t.id_user = u.id "
                + "JOIN project p ON t.id_project = p.id "
                + "JOIN status s ON t.id_status = s.id";
        Connection conn = MySQLConfig.getConnection();
        try {
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery(); 
            
            while (rs.next()) {
                TaskEntity task = new TaskEntity();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setStartDate(rs.getTimestamp("startDate"));
                task.setEndDate(rs.getTimestamp("endDate"));
                
                UserEntity user = new UserEntity();
                user.setId(rs.getInt("userId"));
                user.setLastName(rs.getString("lastName"));
                task.setUser(user);
                
                ProjectEntity project = new ProjectEntity();
                project.setId(rs.getInt("projectId"));
                project.setName(rs.getString("projectName"));
                task.setProject(project);
                
                StatusEntity status = new StatusEntity();
                status.setId(rs.getInt("statusId"));
                status.setName(rs.getString("statusName"));
                task.setStatus(status);
                
                tasks.add(task);
            }
        } catch (SQLException e) {
        	System.out.println("Error " + e.getMessage());
        }
        
        return tasks;
    }

    // Thêm nhiệm vụ mới
    public void addTask(TaskEntity task) {
        String sql = "INSERT INTO tasks (name, start_date, end_date, user_id, project_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, task.getName());
            ps.setTimestamp(2, task.getStartDate());
            ps.setTimestamp(3, task.getEndDate());
            ps.setInt(4, task.getUser().getId());
            ps.setInt(5, task.getProject().getId());
            ps.setInt(6, task.getStatus().getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật nhiệm vụ
    public void updateTask(TaskEntity task) {
        String sql = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, user_id = ?, project_id = ?, status_id = ? WHERE id = ?";

        try (Connection conn = MySQLConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, task.getName());
            ps.setTimestamp(2, task.getStartDate());
            ps.setTimestamp(3, task.getEndDate());
            ps.setInt(4, task.getUser().getId());
            ps.setInt(5, task.getProject().getId());
            ps.setInt(6, task.getStatus().getId());
            ps.setInt(7, task.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa nhiệm vụ theo ID
    public int deleteTaskById(int id) {
    	int rowCount = 0;
    	String sql = "DELETE FROM task t WHERE t.id = ?";

        try (Connection conn = MySQLConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    // Lấy nhiệm vụ theo ID
    public TaskEntity getTaskById(int id) {
        TaskEntity task = null;
        String query = "SELECT t.id, t.name, t.start_date AS startDate, t.end_date AS endDate, "
                     + "u.id AS userId, u.name AS userName, "
                     + "p.id AS projectId, p.name AS projectName, "
                     + "s.id AS statusId, s.name AS statusName "
                     + "FROM tasks t "
                     + "JOIN users u ON t.id_user = u.id "
                     + "JOIN projects p ON t.id_project = p.id "
                     + "JOIN statuses s ON t.id_status = s.id "
                     + "WHERE t.id = ?";
        
        try (Connection conn = MySQLConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    task = new TaskEntity();
                    task.setId(rs.getInt("id"));
                    task.setName(rs.getString("name"));
                    task.setStartDate(rs.getTimestamp("startDate"));
                    task.setEndDate(rs.getTimestamp("endDate"));
                    
                    UserEntity user = new UserEntity();
                    user.setId(rs.getInt("userId"));
                    user.setFirstName(rs.getString("userName"));
                    task.setUser(user);
                    
                    ProjectEntity project = new ProjectEntity();
                    project.setId(rs.getInt("projectId"));
                    project.setName(rs.getString("projectName"));
                    task.setProject(project);
                    
                    StatusEntity status = new StatusEntity();
                    status.setId(rs.getInt("statusId"));
                    status.setName(rs.getString("statusName"));
                    task.setStatus(status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return task;
    }
}
