package com.backend.api.view;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.backend.api.model.enums.ProfileEnum;

public class NewUserView implements Serializable {
	private static final long serialVersionUID = 545340861749922712L;

	private Long id;

	@NotEmpty(message = "'${validatedValue}' Preenchimento obrigatório")
	@Size(min = 3, max = 150, message = "O tamanho deve ser entre {min} a {max} caracteres")
	private String name;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String password;

	private ProfileEnum profile;
//	private String crmvNumber;
//	private String crmvUf;
	private Boolean enabled;
	private OffsetDateTime creationAt;
	private OffsetDateTime updatedAt;
	private String avatar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public OffsetDateTime getCreationAt() {
		return creationAt;
	}

	public void setCreationAt(OffsetDateTime creationAt) {
		this.creationAt = creationAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
