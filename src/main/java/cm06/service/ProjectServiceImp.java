package cm06.service;

import java.sql.Timestamp;
import java.util.List;

import cm06.enity.ProjectEntity;

public interface ProjectServiceImp {

	List<ProjectEntity> getProject();
	
	boolean deleteProject(int id);

	void addProject(String name, Timestamp start_date, Timestamp end_date);

	boolean updateProject(String id, String name, Timestamp end_date);

	ProjectEntity getProjectById(String id);

}
