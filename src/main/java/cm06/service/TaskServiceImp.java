package cm06.service;

import cm06.enity.ProjectEntity;
import cm06.enity.TaskEntity;
import cm06.enity.UserEntity;

import java.util.List;

public interface TaskServiceImp {
    void addTask(TaskEntity task);
    void updateTask(TaskEntity task);
    boolean deleteTaskById(int id);
    TaskEntity getTaskById(int id);
    List<TaskEntity> getTasks();
    List<UserEntity> getUser();
    List<ProjectEntity> getProject();
}

