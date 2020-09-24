package com.backend.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.api.filter.VeterinaryFilter;
import com.backend.api.model.Veterinary;

public interface IVeterinaryRepository extends JpaRepository<Veterinary, Long> {

	public Optional<Veterinary> findByEmail(String email);

	@Query("SELECT v FROM Veterinary v WHERE 1 = 1"
			+ " AND (:#{#filter.name} is null or v.name like %:#{#filter.name}%)"
			+ " AND (:#{#filter.specialties} is null or v.specialties in :#{#filter.specialties})")
	public Page<Veterinary> findVetByFilter(@Param("filter") VeterinaryFilter filter, Pageable pageable);

}
