package com.vitorrubenich.med.controller;

import com.vitorrubenich.med.dto.DtoUpdateDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.vitorrubenich.med.dto.DtoDoctor;
import com.vitorrubenich.med.dto.DtoDoctorList;
import com.vitorrubenich.med.model.Doctor;
import com.vitorrubenich.med.repository.DoctorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DtoDoctor dados) {
		doctorRepository.save(new Doctor(dados));
	}

	@GetMapping
	public Page<DtoDoctorList> listar(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
		return doctorRepository.findAllByAtivoTrue(pagination).map(DtoDoctorList::new);
	}

	@PutMapping
	@Transactional
	public void updateDoctor(@RequestBody @Valid DtoUpdateDoctor dtoUpdateDoctor){
		var doctor = doctorRepository.getReferenceById(dtoUpdateDoctor.id());
		doctor.atualizarDados(dtoUpdateDoctor);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deleteDoctor(@PathVariable Long id){
		var doctor = doctorRepository.getReferenceById(id);
		doctor.inativar();
	}
}
