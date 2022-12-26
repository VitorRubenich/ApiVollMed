package com.vitorrubenich.med.controller;

import com.vitorrubenich.med.dto.DtoAuthLogin;
import com.vitorrubenich.med.dto.DtoToken;
import com.vitorrubenich.med.model.User;
import com.vitorrubenich.med.service.TokenService;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody @Valid DtoAuthLogin dtoAuthLogin){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dtoAuthLogin.login(), dtoAuthLogin.senha());
        var authentication = authManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DtoToken(tokenJWT));
    }
}
