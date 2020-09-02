package com.backend.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.api.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);

}
