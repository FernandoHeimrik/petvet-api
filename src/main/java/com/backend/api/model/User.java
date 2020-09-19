package com.backend.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.backend.api.model.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class User implements Serializable {
	private static final long serialVersionUID = 4549285375896276415L;

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

//	@Column(name = "CRMV_NUMBER")
//	private String crmvNumber;

//	@Column(name = "CRMV_UF")
//	private String crmvUf;

	@Column(name = "ENABLED", nullable = false)
	private Boolean enabled;

	@Column(name = "CREATION_AT")
	private OffsetDateTime creationAt;

	@Column(name = "UPDATED_AT")
	private OffsetDateTime updatedAt;

	@Column(name = "AVATAR")
	private String avatar;

	public User() {
		this.enabled = true;
		this.creationAt = OffsetDateTime.now();
		this.updatedAt = OffsetDateTime.now();
	}

	public User(String name, String email, String password, @NotNull ProfileEnum profile, String crmvNumber,
			String crmvUf) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = true;
		this.creationAt = OffsetDateTime.now();
		this.updatedAt = OffsetDateTime.now();
	}

	public User(String name, String email, String password, @NotNull ProfileEnum profile) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = true;
		this.creationAt = OffsetDateTime.now();
		this.updatedAt = OffsetDateTime.now();
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

	@JsonIgnore
	@Transient
	public boolean isInactive() {
		return !this.enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
