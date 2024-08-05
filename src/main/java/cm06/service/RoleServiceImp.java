package cm06.service;

import java.util.List;

import cm06.enity.RolesEntity;
import cm06.enity.UserEntity;

public interface RoleServiceImp {
	List<RolesEntity> getRole();
	List<UserEntity> getUser();

}
