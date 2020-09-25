package com.backend.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.backend.api.model.Veterinary;

public class UserSystem extends User {
	private static final long serialVersionUID = -2269141654463709992L;

	private com.backend.api.model.User user;
	private Veterinary vet;

	public UserSystem(com.backend.api.model.User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.user = user;
	}

	public UserSystem(Veterinary vet, Collection<? extends GrantedAuthority> authorities) {
		super(vet.getEmail(), vet.getPassword(), authorities);
		this.vet = vet;
	}

	public com.backend.api.model.User getUser() {
		return user;
	}

	public Veterinary getVet() {
		return vet;
	}
}
