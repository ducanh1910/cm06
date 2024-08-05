package cm06.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm06.enity.RolesEntity;
import cm06.enity.UserEntity;
import cm06.repository.UserRepository;
import cm06.service.UserService;
import cm06.service.UserServiceImp;

@WebServlet(name = "userController", urlPatterns = {"/user-add","/user"})
public class UserController extends HttpServlet {
	
	private UserServiceImp userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		//Khai báo cookie
//		Cookie cookie = new Cookie("demo", "HelloCookie");
//		Cookie demo2 = new Cookie("demo2", "HelloCookie2");
//		
//		//Tạo cookie ở client
//		resp.addCookie(demo2);
//		resp.addCookie(cookie);
		
//		Lấy cookie
//		Cookie[] cookies = req.getCookies();
//		for (Cookie cookie : cookies) {
//			//Lấy tên cookie
//			String nameCookie = cookie.getName();
//			//Lấy giá trị của cookie
//			String valueCookie = cookie.getValue();
//			
//			if (nameCookie.equals("demo1")) {
//				System.out.println("Gia tri cookie " + valueCookie);
//				break;
//			}
//			System.out.println("kasd " + valueCookie);
//			
//		}
		String path = req.getServletPath();
		switch (path) {
		case "/user-add":
			userAdd(req,resp);
			break;
		
		case "/user":
			getUser(req, resp);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
		
	}
	
	private void userAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		if (id == null || id.isEmpty()) {
            // Thêm mới người dùng
			String password = req.getParameter("password");
			String first_name = req.getParameter("first_name");
	        String last_name = req.getParameter("last_name");
	        String username = req.getParameter("username");
	        String phone = req.getParameter("phone");
	        String id_role = req.getParameter("roleId");
			
	        UserService user = new UserService();
	        user.addUser(password, first_name, last_name, username, phone, id_role);
	        
	        List<RolesEntity> roles = userService.getAllRole();
			req.setAttribute("roles", roles);
				
        } else {
            // Cập nhật thông tin người dùng
        	String first_name = req.getParameter("first_name");
	        String last_name = req.getParameter("last_name");
	        String phone = req.getParameter("phone");
	        
	        UserService user = new UserService();
	        user.updateUser(id, first_name, last_name, phone);
	    }   
	        req.getRequestDispatcher("user-add.jsp").forward(req, resp);
        
	}
	
	private void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("users", userService.getUser());
		
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
	
	

}
