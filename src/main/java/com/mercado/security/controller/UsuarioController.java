package com.mercado.security.controller;

import com.mercado.security.dto.AuthenticationRequest;
import com.mercado.security.dto.AuthenticationResponse;
import com.mercado.security.dto.UsuarioTO;
import com.mercado.security.entity.Usuario;
import com.mercado.security.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping("/registro")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        String key= usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(key));
        usuario.setFecha(LocalDateTime.now());

        usuarioRepository.insert(usuario);
        return ResponseEntity.ok(usuario);
    }
}
