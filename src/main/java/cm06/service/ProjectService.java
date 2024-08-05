package cm06.service;

import java.sql.Timestamp;
import java.util.List;

import cm06.enity.ProjectEntity;
import cm06.enity.UserEntity;
import cm06.repository.ProjectRepository;

public class ProjectService implements ProjectServiceImp{
	
	private ProjectRepository projectRepository = new ProjectRepository();
	
	@Override
    public boolean updateProject(String id, String name, Timestamp end_date) {
		 try {
	            int count = projectRepository.updateProject(id, name, end_date);
	            return count > 0;
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	            return false;
	        }
		 }
	@Override
	public ProjectEntity getProjectById(String id) {
        return projectRepository.getProjectById(id);
    }
	
	@Override
	public void addProject(String name, Timestamp start_date, Timestamp end_date) {
	    try {
	        projectRepository.addProject(name, start_date, end_date);
	    } catch (Exception e) {
	        System.out.println("Error : " + e.getMessage());
	    }
	}
	
	@Override
	public List<ProjectEntity> getProject() {
		return projectRepository.getProject();
	}

	@Override
	public boolean deleteProject(int id) {
        int count = projectRepository.deleteProjectById(id);
		
		return count > 0;
	}

}
