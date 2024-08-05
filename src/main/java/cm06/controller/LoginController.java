package cm06.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cm06.config.MySQLConfig;
import cm06.enity.UserEntity;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    String role = (session != null) ? (String) session.getAttribute("userRole") : null;

	    if (role == null || !role.equals("admin")) {
	        response.sendRedirect("login.jsp");
	        return;
	    }

	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Bước 1 : Nhận tham số
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			// Bước 2 : Chuẩn bị câu vấn tương ứng với tính năng 
			String query = "SELECT *\n "
					+ "FROM users u\n"
					+ "WHERE u.username = '" + email +"' AND u.password = '"+ password +"' ";
			
			// Bước 3: Mở CSDL và truyển câu truy vấn để thực hiện
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
		    
			//excuteQuery: Gianh cho lay du lieu -> SELECT
			//excuteUpdate: khac SELECT
			//resultset Dai dien cho ket qua du lieu truy van duoc trong bang
			ResultSet resultSet = preparedStatement.executeQuery();
			//tạo ra mảng rỗng để gắn dữ liệu bằng từng dòng dữ liệu truy vấn được
			List<UserEntity> listUsers = new ArrayList<UserEntity>();
			
			while (resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setFirstName(resultSet.getString("first_name"));
				
				listUsers.add(userEntity);
			}
			
			if (listUsers.size() > 0) {
				Cookie logined = new Cookie("logined", "true");
				resp.addCookie(logined);
				
				String context = req.getContextPath();
				
				resp.sendRedirect(context +"/user-add");
			} else {
				String context = req.getContextPath();
				resp.sendRedirect(context +"/login");
			}
			
			connection.close();
			
		} catch (Exception e) {
			System.out.println("Loi login " +e.getMessage());
		}
		
	}

}
