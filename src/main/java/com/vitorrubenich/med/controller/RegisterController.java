package com.vitorrubenich.med.controller;

import com.vitorrubenich.med.dto.DtoDoctorDet;
import com.vitorrubenich.med.dto.DtoRegisterUser;
import com.vitorrubenich.med.dto.DtoUserDet;
import com.vitorrubenich.med.model.User;
import com.vitorrubenich.med.repository.UserRepository;
import com.vitorrubenich.med.utils.SenhaUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity doRegister(@RequestBody @Valid DtoRegisterUser dtoRegisterUser,  UriComponentsBuilder uriBuilder){

        String senhaEncriptada = SenhaUtils.encode(dtoRegisterUser.senha());
        DtoRegisterUser registerUser = new DtoRegisterUser(senhaEncriptada, dtoRegisterUser.login());
        var user = new User(registerUser);

        userRepository.save(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        var dto = new DtoUserDet(user);
        return ResponseEntity.created(uri).body(dto);
    }

}
