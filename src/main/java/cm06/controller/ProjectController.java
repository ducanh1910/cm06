package cm06.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm06.service.ProjectServiceImp;
import cm06.service.UserService;
import cm06.enity.RolesEntity;
import cm06.service.ProjectService;

@WebServlet(name = "projectController", urlPatterns = {"/groupwork-add","/groupwork"})
public class ProjectController extends HttpServlet{
	
	private ProjectServiceImp projectService = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/groupwork-add":
			groupworkAdd(req,resp);
			break;
		
		case "/groupwork":
			getProject(req, resp);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
	
	private void groupworkAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String id = req.getParameter("id");
	    if (id == null || id.isEmpty()) {
	        // Thêm mới dự án
	        String name = req.getParameter("name");
	        String startDateString = req.getParameter("start_date");
	        String endDateString = req.getParameter("end_date");

	        Timestamp start_date = null;
	        Timestamp end_date = null;

	        try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	            // Chuyển đổi start_date hoặc sử dụng thời gian hiện tại nếu không được cung cấp
	            if (startDateString != null && !startDateString.isEmpty()) {
	                Date parsedStartDate = dateFormat.parse(startDateString);
	                start_date = new Timestamp(parsedStartDate.getTime());
	            } else {
	                start_date = new Timestamp(System.currentTimeMillis());
	            }

	            // Chuyển đổi end_date
	            if (endDateString != null && !endDateString.isEmpty()) {
	                Date parsedEndDate = dateFormat.parse(endDateString);
	                end_date = new Timestamp(parsedEndDate.getTime());
	            } else {
	                throw new IllegalArgumentException("end_date không được để trống");
	            }
	        } catch (ParseException e) {
	            // Xử lý trường hợp định dạng ngày không chính xác
	            System.out.println("Lỗi: Định dạng ngày không hợp lệ");
	        } catch (IllegalArgumentException e) {
	            // Xử lý trường hợp end_date bị trống
	            System.out.println("Lỗi: " + e.getMessage());
	        }

	        // Chỉ thêm dự án nếu end_date không phải là null
	        if (end_date != null) {
	            ProjectService projectService = new ProjectService();
	            projectService.addProject(name, start_date, end_date);
	        }

	    } else {
	    	// Cập nhật dự án hiện có (đoạn mã đã được chú thích)
	         String name = req.getParameter("name");
	         String startDateString = req.getParameter("start_date");
		     String endDateString = req.getParameter("end_date");

		     Timestamp start_date = null;
		     Timestamp end_date = null;
		     
		     try {
		            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		            // Chuyển đổi start_date hoặc sử dụng thời gian hiện tại nếu không được cung cấp
		            if (startDateString != null && !startDateString.isEmpty()) {
		                Date parsedStartDate = dateFormat.parse(startDateString);
		                start_date = new Timestamp(parsedStartDate.getTime());
		            } else {
		                start_date = new Timestamp(System.currentTimeMillis());
		            }

		            // Chuyển đổi end_date
		            if (endDateString != null && !endDateString.isEmpty()) {
		                Date parsedEndDate = dateFormat.parse(endDateString);
		                end_date = new Timestamp(parsedEndDate.getTime());
		            } else {
		                throw new IllegalArgumentException("end_date không được để trống");
		            }
		        } catch (ParseException e) {
		            // Xử lý trường hợp định dạng ngày không chính xác
		            System.out.println("Lỗi: Định dạng ngày không hợp lệ");
		        } catch (IllegalArgumentException e) {
		            // Xử lý trường hợp end_date bị trống
		            System.out.println("Lỗi: " + e.getMessage());
		        }

	         ProjectService projectService = new ProjectService();
	         projectService.updateProject(id, name, end_date);
	    }
	    req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}



	
    private void getProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projects", projectService.getProject());
		
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}
}
