package com.backend.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.backend.api.model.enums.ProfileEnum;

@Entity
@Table(name = "VETERINARY")
public class Veterinary implements Serializable {
	private static final long serialVersionUID = -4097084574268195730L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "EMAIL", unique = true, nullable = false, length = 255)
	private String email;

	@Column(name = "PASSWORD", nullable = false, length = 255)
	private String password;

	@Column(name = "PROFILE", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private ProfileEnum profile;

	@Column(name = "ENABLED", nullable = false)
	private Boolean enabled;

	@Column(name = "CREATION_AT")
	private OffsetDateTime creationAt;

	@Column(name = "UPDATED_AT")
	private OffsetDateTime updatedAt;

	@Column(name = "AVATAR")
	private String avatar;

	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "VETERINARY_SPECIALTIES", joinColumns = @JoinColumn(name = "VETERINARY_ID"), inverseJoinColumns = @JoinColumn(name = "SPECIALTY_ID"))

	@ElementCollection
	@CollectionTable(name = "SPECIALTIES", joinColumns = @JoinColumn(name = "VET_ID"))
	@Column(name = "SPECIALTY")
	private Set<String> specialties = new HashSet<>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(Set<String> specialties) {
		this.specialties = specialties;
	}

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
