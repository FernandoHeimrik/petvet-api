package com.backend.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.api.model.User;
import com.backend.api.model.Veterinary;
import com.backend.api.repository.IUserRepository;
import com.backend.api.repository.IVeterinaryRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IVeterinaryRepository vetRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOption = userRepository.findByEmail(email);
		if (userOption.isPresent()) {
			User user = userOption.get();
			return new UserSystem(user, getPermissions(user));
		} else {
			Optional<Veterinary> vetOption = vetRepository.findByEmail(email);
			if (vetOption.isPresent()) {
				Veterinary vet = vetOption.get();
				return new UserSystem(vet, getPermissions(vet));
			} else {
				throw new UsernameNotFoundException("invalid.login");
			}
		}
	}

	private Collection<? extends GrantedAuthority> getPermissions(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(user.getProfile().toString().toUpperCase()));
		return authorities;
	}

	private Collection<? extends GrantedAuthority> getPermissions(Veterinary vet) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(vet.getProfile().toString().toUpperCase()));
		return authorities;
	}
}
