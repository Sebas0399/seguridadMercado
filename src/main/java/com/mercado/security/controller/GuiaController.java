package com.mercado.security.controller;

import com.mercado.security.dto.ProductosTO;
import com.mercado.security.entity.Guia;
import com.mercado.security.entity.Producto;
import com.mercado.security.repository.GuiaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/guias")
@CrossOrigin
public class GuiaController {
    @Autowired
    GuiaRepository guiaRepository;
    @GetMapping
    public ResponseEntity<List<Guia>>findAll(){

        List<Guia>guias=this.guiaRepository.findAll();
        if(!guias.isEmpty()){
            return ResponseEntity.ok(guias);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Guia>createOne(@RequestBody @Valid Guia guia){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.guiaRepository.save(guia)
        );
    }


    @PostMapping(path = "save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guia>guardarGuia(@RequestBody Guia guia){
        guia.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.guiaRepository.save(guia)
        );
    }

    @GetMapping(path = "buscarAD",consumes=MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<Producto>>buscarPorFechasNombre(@RequestBody ProductosTO productos){
        System.out.println("esta vivo");
        List<Guia> listaGuias=guiaRepository.findGuiasByFechas(productos.getFechaInicio(), productos.getFechaFin());
        List<Producto> listaFinal=new ArrayList<>();

        if (productos.getNombre().isEmpty()){
            for (Guia guias : listaGuias) {
                List<Producto> listaProductos=guias.getProductos();

                for (Producto prod : listaProductos) {
                    listaFinal.add(prod);
                }
            }
        }else{
            for (Guia guias : listaGuias) {
                List<Producto> listaProductos=guias.getProductos();
                for (Producto prod : listaProductos) {
                    if (prod.getNombre().equals(productos.getNombre())) {
                        listaFinal.add(prod);
                    }
                }
            }
        }

        return ResponseEntity.ok(listaFinal);
    }


    @GetMapping(path = "consulta2",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<Guia>>buscarPorFechas2(@RequestBody ProductosTO productos){
        List<Guia> listaGuias=guiaRepository.findGuiasByFechas(productos.getFechaInicio(), productos.getFechaFin());
        return ResponseEntity.ok(listaGuias);
    }



}
