package cm06.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cm06.enity.UserEntity;
import cm06.service.UserService;
import cm06.service.UserServiceImp;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    private UserServiceImp userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        UserEntity loggedUser = (UserEntity) session.getAttribute("loggedUser");
        UserEntity user = userService.getUserById(loggedUser.getId());

        req.setAttribute("user", user);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
