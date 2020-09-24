package com.backend.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.backend.api.filter.VeterinaryFilter;
import com.backend.api.model.Veterinary;
import com.backend.api.service.IVeterinaryService;
import com.backend.api.service.exception.EmailUserAlreadyRegisteredException;
import com.backend.api.view.NewVeterinaryView;
import com.backend.api.view.VeterinaryView;

@RestController
@RequestMapping("/vets")
public class VeterinaryResource {

	private ModelMapper modelMapper;

	private IVeterinaryService veterinaryService;

	public VeterinaryResource(ModelMapper modelMapper, IVeterinaryService veterinaryService) {
		this.modelMapper = modelMapper;
		this.veterinaryService = veterinaryService;
	}

	@PostMapping("/save")
	public ResponseEntity<NewVeterinaryView> createVeterinary(@Valid @RequestBody NewVeterinaryView newVetView) {
		try {
			Veterinary vet = modelMapper.map(newVetView, Veterinary.class);
			vet = veterinaryService.createVeterinary(vet);
			newVetView.setId(vet.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(newVetView);

		} catch (EmailUserAlreadyRegisteredException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email.not.available", exc);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<VeterinaryView> getVeterinary(@PathVariable Long id) {
		Veterinary vet = veterinaryService.getVetById(id);
		if (vet == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(convertToView(vet));
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteVeterinary(@PathVariable Long id) {
		veterinaryService.deleteVet(id);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<VeterinaryView>> getVeterinarys(VeterinaryFilter filter,Pageable pageable) {
		Page<Veterinary> vets = veterinaryService.getVetsList(filter, pageable);
		List<VeterinaryView> vetsView = vets.stream().map(this::convertToView).collect(Collectors.toList());
		Page<VeterinaryView> page = new PageImpl<>(vetsView);
		return ResponseEntity.ok(page);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VeterinaryView> updateVeterinary(@PathVariable Long id,
			@Valid @RequestBody VeterinaryView vetView) {
		Veterinary vet = convertToEntity(vetView);
		veterinaryService.updateVet(id, vet);
		return ResponseEntity.ok(vetView);
	}

	@PutMapping("/{id}/enabled")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateVeterinaryActive(@PathVariable Long id, @RequestBody Boolean enabled) {
		veterinaryService.updateVetActive(id, enabled);

	}

	private VeterinaryView convertToView(Veterinary vet) {
		VeterinaryView vetView = modelMapper.map(vet, VeterinaryView.class);
		return vetView;
	}

	private Veterinary convertToEntity(VeterinaryView vetView) {
		Veterinary vet = modelMapper.map(vetView, Veterinary.class);
		return vet;
	}

}
