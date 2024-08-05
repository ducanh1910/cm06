package cm06.service;

import java.util.List;

import cm06.enity.RolesEntity;
import cm06.enity.UserEntity;
import cm06.repository.RoleRepository;
import cm06.repository.UserRepository;

public class RoleService implements RoleServiceImp{
	
	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();

	@Override
	public List<RolesEntity> getRole() {
		return roleRepository.getRole();
	}

	@Override
	public List<UserEntity> getUser() {
		return userRepository.getUser();
	}

}
