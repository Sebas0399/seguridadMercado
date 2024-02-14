package com.mercado.security.controller;

import com.mercado.security.dto.AuthenticationRequest;
import com.mercado.security.dto.AuthenticationResponse;
import com.mercado.security.entity.Usuario;
import com.mercado.security.repository.UsuarioRepository;
import com.mercado.security.service.AuthenticationService;
import com.mercado.security.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
@Autowired
private JwtService jwtService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity <AuthenticationResponse>login(@RequestBody @Valid AuthenticationRequest authRequest){

        try {
            AuthenticationResponse jwtDto=authenticationService.login(authRequest);
            return ResponseEntity.ok(jwtDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(new AuthenticationResponse("Error de autenticación"));
        }


    }

    @PostMapping(value = "/validarToken",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean>validarSession(@RequestBody AuthenticationResponse token)
    { System.out.println(token);
        var valido=jwtService.isTokenValid(token.getJwt());
        if(valido){
            return ResponseEntity.ok(valido);
        }
        else{
            return new ResponseEntity<Boolean>(valido, HttpStatusCode.valueOf(403));
    }

    }
    @GetMapping("/all")
    public ResponseEntity <List<Usuario>>login(){
        List<Usuario> lista=usuarioRepository.findAll();
        return ResponseEntity.ok(lista);
    }

}
