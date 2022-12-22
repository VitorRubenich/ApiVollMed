package med.vr.api.controller;

import med.vr.api.dto.DadosCadastroDoctor;
import med.vr.api.model.Doctor;
import med.vr.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void create(@RequestBody DadosCadastroDoctor doc)
    {
        Doctor doctor = new Doctor(doc);
        doctorRepository.save(doctor);
    }
}
