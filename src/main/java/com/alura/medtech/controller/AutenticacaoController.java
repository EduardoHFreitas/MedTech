package com.alura.medtech.controller;

import com.alura.medtech.infra.security.TokenDTO;
import com.alura.medtech.model.usuario.Usuario;
import com.alura.medtech.model.usuario.UsuarioDTO;
import com.alura.medtech.service.TokenService;
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
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO usuario) {
        var token = new UsernamePasswordAuthenticationToken(usuario.login(), usuario.senha());

        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok().body(new TokenDTO(tokenJWT));
    }
}
