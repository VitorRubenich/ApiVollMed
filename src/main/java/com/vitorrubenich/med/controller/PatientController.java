package com.vitorrubenich.med.controller;

import java.util.List;

import com.vitorrubenich.med.dto.DtoUpdatePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.vitorrubenich.med.dto.DtoPatient;
import com.vitorrubenich.med.dto.DtoPatientList;
import com.vitorrubenich.med.model.Patient;
import com.vitorrubenich.med.repository.PatientRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DtoPatient dadosPaciente) {
		patientRepository.save(new Patient(dadosPaciente));
	}

	@GetMapping
	public Page<DtoPatientList> listar(@PageableDefault(sort = {"name"}, size = 10) Pageable pagination){
		return patientRepository.findAll(pagination).map(DtoPatientList::new);
	}

	@PutMapping
	@Transactional
	public void updatePatient(@RequestBody @Valid DtoUpdatePatient dtoUpdatePatient){
		var patient = patientRepository.getReferenceById(dtoUpdatePatient.id());
		patient.updatePatient(dtoUpdatePatient);

	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletePatient(@PathVariable Long id){
		var patient = patientRepository.getReferenceById(id);
		patient.inativar();
	}
}
