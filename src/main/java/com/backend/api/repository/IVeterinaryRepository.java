package com.backend.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.api.filter.VeterinaryFilter;
import com.backend.api.model.Veterinary;

public interface IVeterinaryRepository extends JpaRepository<Veterinary, Long> {

	public Optional<Veterinary> findByEmail(String email);

	@Query("FROM Veterinary v WHERE v.name like %:filter.name%" + " AND v.specialties in :filter.specialties")
	public Page<Veterinary> findVetByFilter(VeterinaryFilter filter, Pageable pageable);

}
