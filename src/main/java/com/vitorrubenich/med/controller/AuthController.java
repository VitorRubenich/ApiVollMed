package com.vitorrubenich.med.controller;

import com.vitorrubenich.med.dto.DtoAuthLogin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody @Valid DtoAuthLogin dtoAuthLogin){
        var token = new UsernamePasswordAuthenticationToken(dtoAuthLogin.login(), dtoAuthLogin.senha());
        var authentication = authManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
