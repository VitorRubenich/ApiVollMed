package com.vitorrubenich.med.controller;

import com.vitorrubenich.med.dto.DtoDoctorDet;
import com.vitorrubenich.med.dto.DtoUpdateDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.vitorrubenich.med.dto.DtoDoctor;
import com.vitorrubenich.med.dto.DtoDoctorList;
import com.vitorrubenich.med.model.Doctor;
import com.vitorrubenich.med.repository.DoctorRepository;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;

	@PostMapping
	@Transactional
	public ResponseEntity postDoctor(@RequestBody @Valid DtoDoctor dados, UriComponentsBuilder uriBuilder) {
		var doc = new Doctor(dados);
		doctorRepository.save(doc);

		var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doc.getId()).toUri();

		var dto = new DtoDoctorDet(doc);
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping
	public ResponseEntity<Page<DtoDoctorList>> getDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
		var page = doctorRepository.findAllByAtivoTrue(pagination).map(DtoDoctorList::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity putDoctor(@RequestBody @Valid DtoUpdateDoctor dtoUpdateDoctor){
		var doctor = doctorRepository.getReferenceById(dtoUpdateDoctor.id());
		doctor.atualizarDados(dtoUpdateDoctor);
		return ResponseEntity.ok(new DtoDoctorDet(doctor));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteDoctor(@PathVariable Long id){
		var doctor = doctorRepository.getReferenceById(id);
		doctor.inativar();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity getDoctor(@PathVariable Long id){

		var doctor = doctorRepository.getReferenceById(id);
		var dtoDoctor = new DtoDoctorDet(doctor);
		return ResponseEntity.ok(dtoDoctor);
	}
}
