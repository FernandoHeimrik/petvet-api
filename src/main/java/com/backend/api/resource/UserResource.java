package com.backend.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.backend.api.model.User;
import com.backend.api.service.IUserService;
import com.backend.api.service.exception.EmailUserAlreadyRegisteredException;
import com.backend.api.view.NewUserView;
import com.backend.api.view.UserView;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private IUserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/save")
	public ResponseEntity<NewUserView> createUser(@Valid @RequestBody NewUserView newUserView) {
		try {
			User user = modelMapper.map(newUserView, User.class);
			user = userService.createUser(user);
			newUserView.setId(user.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(newUserView);

		} catch (EmailUserAlreadyRegisteredException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email.not.available", exc);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserView> getUser(@PathVariable Long id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(convertToView(user));
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@GetMapping("/list")
	public ResponseEntity<List<UserView>> getUsers() {
		List<User> users = userService.getUsersList();
		return ResponseEntity.ok().body(users.stream().map(this::convertToView).collect(Collectors.toList()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserView> updateUser(@PathVariable Long id, @Valid @RequestBody UserView userView) {
		User user = convertToEntity(userView);
		userService.updateUser(id, user);
		return ResponseEntity.ok(userView);
	}

	@PutMapping("/{id}/enabled")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUserActive(@PathVariable Long id, @RequestBody Boolean enabled) {
		userService.updateUserActive(id, enabled);

	}

	private UserView convertToView(User user) {
		UserView userView = modelMapper.map(user, UserView.class);
		return userView;
	}

	private User convertToEntity(UserView userView) {
		User user = modelMapper.map(userView, User.class);
		return user;
	}
}
