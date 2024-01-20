package com.mercado.security.controller;

import com.mercado.security.entity.Canton;
import com.mercado.security.entity.Parroquia;
import com.mercado.security.entity.Provincia;
import com.mercado.security.repository.CantonRepository;
import com.mercado.security.repository.ParroquiaRepository;
import com.mercado.security.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datos")
@CrossOrigin
public class ProvinciaController {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private CantonRepository cantonRepository;

    @Autowired
    private ParroquiaRepository parroquiaRepository;

    @PostMapping("/insertar")
    public String insertarDatos(@RequestBody List<Provincia> provincias) {
        System.out.println("Hola meko");
        for (Provincia provincia : provincias) {
            Provincia savedProvincia = provinciaRepository.save(provincia);
            for (Canton canton : savedProvincia.getCantones()) {
                canton.setProvincia(savedProvincia);
                Canton savedCanton = cantonRepository.save(canton);
                for (Parroquia parroquia : savedCanton.getParroquias()) {
                    parroquia.setCanton(savedCanton);
                    parroquiaRepository.save(parroquia);
                }
            }
        }
        return "Funciona";
    }
}
