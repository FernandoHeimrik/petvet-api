package com.backend.api.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.backend.api.model.User;
import com.backend.api.model.enums.ProfileEnum;
import com.backend.api.repository.IUserRepository;
import com.backend.api.service.IUserService;
import com.backend.api.service.exception.EmailUserAlreadyRegisteredException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(@Valid User user) {
		Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
		if (savedUser.isPresent()) {
			throw new EmailUserAlreadyRegisteredException();
		}
		if (user.getProfile().equals(ProfileEnum.ROLE_VET)) {
			Assert.notNull(user.getCrmvNumber(), "user.service.crmv.number.null");
			Assert.notNull(user.getCrmvUf(), "user.service.crmv.uf.null");
			// user.setEnabled(false); TODO: necessario validacao de crmv para ativar
			// veterinario https://app.cfmv.gov.br/paginas/busca
		}
		user.setEnabled(true);
		user.setCreationAt(OffsetDateTime.now());
		user.setUpdatedAt(OffsetDateTime.now());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public List<User> getUsersList() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Long id, User user) {
		User savedUser = getUserById(id);
		BeanUtils.copyProperties(user, savedUser, "id", "password");
		return userRepository.save(savedUser);
	}

	@Override
	public void updateUserActive(Long id, Boolean enabled) {
		User savedUser = getUserById(id);
		savedUser.setEnabled(enabled);
		userRepository.save(savedUser);

	}

}
