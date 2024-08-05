package cm06.service;
import java.util.List;

import cm06.enity.RolesEntity;
import cm06.enity.UserEntity;

public interface UserServiceImp {
	
	List<RolesEntity> getAllRole();
	
	List<UserEntity> getUser();
	
	boolean deleteUser(int id);
	
	void addUser(String password, String first_name, String last_name, String username, String phone, String id_role);

	boolean updateUser(String id, String firstName, String lastName, String phone);

	UserEntity getUserById(int id);
	
	

}
