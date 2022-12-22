package com.vitorrubenich.med.controller;

import java.util.List;

import com.vitorrubenich.med.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.vitorrubenich.med.model.Patient;
import com.vitorrubenich.med.repository.PatientRepository;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DtoPatient dadosPaciente, UriComponentsBuilder uriBuilder) {
		var patient = new Patient(dadosPaciente);
		patientRepository.save(patient);
		var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

		var dto = new DtoPatientDet(patient);
		return ResponseEntity.created(uri).body(dto);
	}


	@GetMapping
	public ResponseEntity<Page<DtoPatientList>> listar(@PageableDefault(sort = {"name"}, size = 10) Pageable pagination){
		var page =  patientRepository.findAll(pagination).map(DtoPatientList::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity updatePatient(@RequestBody @Valid DtoUpdatePatient dtoUpdatePatient){
		var patient = patientRepository.getReferenceById(dtoUpdatePatient.id());
		patient.updatePatient(dtoUpdatePatient);
		return ResponseEntity.ok(new DtoPatientDet(patient));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletePatient(@PathVariable Long id){
		var patient = patientRepository.getReferenceById(id);
		patient.inativar();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity getPatient(@PathVariable Long id){
		var patient = patientRepository.getReferenceById(id);
		var dto = new DtoPatientDet(patient);
		return ResponseEntity.ok(dto);

	}
}
