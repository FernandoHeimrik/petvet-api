package com.backend.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VETERINARY")
public class Veterinary extends User {
	private static final long serialVersionUID = -4097084574268195730L;

	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	private String description;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "VETERINARY_SPECIALTIES", joinColumns = @JoinColumn(name = "VETERINARY_ID"), inverseJoinColumns = @JoinColumn(name = "SPECIALTY_ID"))

	@ElementCollection
	@CollectionTable(name = "SPECIALTIES")
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

}
