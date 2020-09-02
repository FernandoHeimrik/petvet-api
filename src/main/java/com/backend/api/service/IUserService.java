package com.backend.api.service;

import java.util.List;

import javax.validation.Valid;

import com.backend.api.model.User;

public interface IUserService {

	User createUser(@Valid User usuario);

	User getUserById(Long id);

	void deleteUser(Long id);

	List<User> getUsersList();

	User updateUser(Long id, User user);

	void updateUserActive(Long id, Boolean active);
}
