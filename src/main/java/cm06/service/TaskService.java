package cm06.service;

import cm06.repository.ProjectRepository;
import cm06.repository.UserRepository;
import cm06.enity.ProjectEntity;
import cm06.enity.TaskEntity;
import cm06.enity.UserEntity;
import cm06.repository.TaskRepository;
import java.util.List;
public class TaskService implements TaskServiceImp{
	
	
	UserRepository userRepository = new UserRepository();
	ProjectRepository projectRepository = new ProjectRepository();
	TaskRepository taskRepository = new TaskRepository();
    
	
	
	@Override
    public void addTask(TaskEntity task) {
        taskRepository.addTask(task);
    }

    @Override
    public void updateTask(TaskEntity task) {
        taskRepository.updateTask(task);
    }

    @Override
    public boolean deleteTaskById(int id) {
    	int count = taskRepository.deleteTaskById(id);
    	return count > 0;
    }

    @Override
    public TaskEntity getTaskById(int id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public List<TaskEntity> getTasks() {
        return taskRepository.getTasks();
    }
    @Override
    public List<UserEntity> getUser() {
        return userRepository.getUser();
    }
    @Override
    public List<ProjectEntity> getProject() {
		return projectRepository.getProject();
	}

}
