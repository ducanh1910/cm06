package cm06.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm06.config.MySQLConfig;
import cm06.enity.RolesEntity;

@WebServlet(name ="roleController", urlPatterns = {"/role-add","/role"})
public class RoleController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("name");
			String description = req.getParameter("description");

			String query = "INSERT INTO roles (name, description) VALUES (?, ?)";
			RolesEntity rolesEntity = new RolesEntity();
			
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
		    preparedStatement.setString(1, name);
		    preparedStatement.setString(2, description);
		    
		    int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Thêm thành công");
            } else {
                System.out.println("Thêm thất bại");
            }

		} catch (Exception e) {
			System.out.println("Lỗi nhập role: " + e.getMessage());
		}
	}
	
	private void userAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
		
}
