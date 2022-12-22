package com.vitorrubenich.med.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorrubenich.med.model.Doctor;

import java.nio.channels.FileChannel;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    Page<Doctor> findAllByAtivoTrue(Pageable pagination);
    Page<Doctor> findAllByAtivoFalse(Pageable pagination);

}
