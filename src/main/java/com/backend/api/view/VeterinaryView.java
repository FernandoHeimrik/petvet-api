package com.backend.api.view;

import java.time.OffsetDateTime;

import com.backend.api.model.enums.ProfileEnum;

public class VeterinaryView extends UserView {
	private static final long serialVersionUID = -6449048938744795172L;
	
	private Long id;
	private String name;
	private String email;
	private ProfileEnum profile;
	private Boolean enabled;
	private OffsetDateTime creationAt;
	private OffsetDateTime updatedAt;
	private String avatar;
	private String description;
	//private Set<String> specialties;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Set<String> getSpecialties() {
//		return specialties;
//	}
//
//	public void setSpecialties(Set<String> specialties) {
//		this.specialties = specialties;
//	}

}
