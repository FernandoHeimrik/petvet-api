package com.backend.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.api.filter.VeterinaryFilter;
import com.backend.api.model.Veterinary;

public interface IVeterinaryService {

	Veterinary createVeterinary(Veterinary vet);

	Veterinary getVetById(Long id);

	void deleteVet(Long id);

	Veterinary updateVet(Long id, Veterinary vet);

	void updateVetActive(Long id, Boolean enabled);

	Page<Veterinary> getVetsList(VeterinaryFilter filter, Pageable pageable);

}
