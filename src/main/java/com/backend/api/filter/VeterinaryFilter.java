package com.backend.api.filter;

import java.io.Serializable;
import java.util.Set;

public class VeterinaryFilter implements Serializable {
	private static final long serialVersionUID = 7248273404719513893L;

	private String name;
	private Set<String> specialties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(Set<String> specialties) {
		this.specialties = specialties;
	}

}
