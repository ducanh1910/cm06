package cm06.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm06.enity.ProjectEntity;
import cm06.enity.StatusEntity;
import cm06.enity.TaskEntity;
import cm06.enity.UserEntity;
import cm06.repository.TaskRepository;
import cm06.service.TaskService;
import cm06.service.TaskServiceImp;

@WebServlet(name = "taskController", urlPatterns = {"/task-add", "/task"})
public class TaskController extends HttpServlet {
    private TaskServiceImp taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/task-add":
                taskAdd(req, resp);
                break;
            case "/task":
                getTasks(req, resp);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + path);
        }
    }

    private void taskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        TaskEntity task = new TaskEntity();

        task.setUser(new UserEntity());
        task.setProject(new ProjectEntity());
        task.setStatus(new StatusEntity());

        task.setName(req.getParameter("name"));
        task.setStartDate(Timestamp.valueOf(req.getParameter("startDate")));
        task.setEndDate(Timestamp.valueOf(req.getParameter("endDate")));

        if (id == null || id.isEmpty()) {
            // Add new task
            task.getUser().setId(Integer.parseInt(req.getParameter("idUser")));
            task.getProject().setId(Integer.parseInt(req.getParameter("idProject")));
            task.getStatus().setId(Integer.parseInt(req.getParameter("idStatus")));
            taskService.addTask(task);
        } else {
            // Update task
            task.setId(Integer.parseInt(id));
            task.getUser().setId(Integer.parseInt(req.getParameter("idUser")));
            task.getProject().setId(Integer.parseInt(req.getParameter("idProject")));
            task.getStatus().setId(Integer.parseInt(req.getParameter("idStatus")));
            taskService.updateTask(task);
        }

        req.getRequestDispatcher("task-add.jsp").forward(req, resp);
    }

    private void getTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	 List<TaskEntity> tasks = taskService.getTasks();
    	    req.setAttribute("tasks", tasks);
    	    req.getRequestDispatcher("task.jsp").forward(req, resp);
    }
}
