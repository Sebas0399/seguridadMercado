package com.mercado.security.controller;

import com.mercado.security.dto.AuthenticationRequest;
import com.mercado.security.dto.AuthenticationResponse;
import com.mercado.security.entity.Usuario;
import com.mercado.security.repository.UsuarioRepository;
import com.mercado.security.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping("/authenticate")
    public ResponseEntity <AuthenticationResponse>login(@RequestBody @Valid AuthenticationRequest authRequest){

        try {
            System.out.println(authRequest);
            AuthenticationResponse jwtDto=authenticationService.login(authRequest);
            return ResponseEntity.ok(jwtDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(new AuthenticationResponse("Error de autenticación"));
        }


    }
    @GetMapping("/public-access")
    public ResponseEntity<String > publicAccessEndPoint(){
        return ResponseEntity.ok("EndPointPublico");
    }

    @GetMapping("/all")
    public ResponseEntity <List<Usuario>>login(){
        List<Usuario> lista=usuarioRepository.findAll();
        return ResponseEntity.ok(lista);
    }

}
