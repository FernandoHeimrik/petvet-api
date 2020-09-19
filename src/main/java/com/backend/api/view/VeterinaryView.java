package com.backend.api.view;

import java.util.Set;

public class VeterinaryView extends UserView {
	private static final long serialVersionUID = -6449048938744795172L;

	private String description;
	private Set<String> specialties;

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
