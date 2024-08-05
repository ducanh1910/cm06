package cm06.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cm06.response.BaseResponse;
import cm06.service.UserService;
import cm06.service.UserServiceImp;

@WebServlet(name = "userApiController", urlPatterns = {"/api/user"})
public class UserApiController extends HttpServlet {
	
	private UserServiceImp userServiceImp = new UserService();
	
	//Gson, Jackson. 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idUser = Integer.parseInt(req.getParameter("id"));
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(200);
		baseResponse.setMessage("");
		baseResponse.setData(userServiceImp.deleteUser(idUser));
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(baseResponse);
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.append(jsonData);
		
		writer.close();
	}

}
