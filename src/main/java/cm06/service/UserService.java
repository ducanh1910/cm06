package cm06.service;

import java.util.List;

import cm06.enity.RolesEntity;
import cm06.enity.UserEntity;
import cm06.repository.RoleRepository;
import cm06.repository.UserRepository;

public class UserService implements UserServiceImp {
	
	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();
	
	@Override
	public List<RolesEntity> getAllRole() {
		return roleRepository.getRole();
	}

	@Override
	public List<UserEntity> getUser() {
		return userRepository.getUser();
	}

	@Override
	public boolean deleteUser(int id) {
		int count = userRepository.deleteUserById(id);
		
		return count > 0;
	}

	@Override
	public void addUser(String password, String first_name, String last_name, String username, String phone, String id_role) {
        try {
            userRepository.addUser(password, first_name, last_name, username, phone, id_role);

            } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
	
	@Override
    public boolean updateUser(String id, String firstName, String lastName, String phone) {
		 try {
	            int count = userRepository.updateUser(id, firstName, lastName, phone);
	            return count > 0;
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	            return false;
	        }
		 }
	@Override
	public UserEntity getUserById(int id) {
        return userRepository.getUserById(id);
    } 
}
