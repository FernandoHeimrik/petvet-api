package com.backend.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserSystem extends User {
	private static final long serialVersionUID = -2269141654463709992L;

	private com.backend.api.model.User user;

	public UserSystem(com.backend.api.model.User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.user = user;
	}

	public com.backend.api.model.User getUser() {
		return user;
	}
}
