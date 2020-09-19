package com.backend.api.service.impl;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.api.filter.VeterinaryFilter;
import com.backend.api.model.User;
import com.backend.api.model.Veterinary;
import com.backend.api.repository.IUserRepository;
import com.backend.api.repository.IVeterinaryRepository;
import com.backend.api.service.IVeterinaryService;
import com.backend.api.service.exception.EmailUserAlreadyRegisteredException;

@Service
public class VeterinaryServiceImpl implements IVeterinaryService {

	private IVeterinaryRepository veterinaryRepository;

	private IUserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	public VeterinaryServiceImpl(IVeterinaryRepository veterinaryRepository, IUserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.veterinaryRepository = veterinaryRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Veterinary createVeterinary(Veterinary vet) {
		Optional<User> savedUser = userRepository.findByEmail(vet.getEmail());
		if (savedUser.isPresent()) {
			throw new EmailUserAlreadyRegisteredException();
		}
		Optional<Veterinary> savedVet = veterinaryRepository.findByEmail(vet.getEmail());
		if (savedVet.isPresent()) {
			throw new EmailUserAlreadyRegisteredException();
		}
		vet.setEnabled(true);
		vet.setCreationAt(OffsetDateTime.now());
		vet.setUpdatedAt(OffsetDateTime.now());
		vet.setPassword(passwordEncoder.encode(vet.getPassword()));
		return veterinaryRepository.save(vet);
	}

	@Override
	public Veterinary getVetById(Long id) {
		Veterinary vet = veterinaryRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return vet;
	}

	@Override
	public void deleteVet(Long id) {
		veterinaryRepository.deleteById(id);

	}

	@Override
	public Veterinary updateVet(Long id, Veterinary vet) {
		Veterinary savedVet = getVetById(id);
		BeanUtils.copyProperties(vet, savedVet, "id", "password");
		return veterinaryRepository.save(savedVet);
	}

	@Override
	public void updateVetActive(Long id, Boolean enabled) {
		Veterinary savedVet = getVetById(id);
		savedVet.setEnabled(enabled);
		veterinaryRepository.save(savedVet);

	}

	@Override
	public Page<Veterinary> getVetsList(VeterinaryFilter filter, Pageable pageable) {
		return veterinaryRepository.findVetByFilter(filter, pageable);
	}

}
