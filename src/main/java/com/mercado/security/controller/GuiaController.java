package com.mercado.security.controller;

import com.mercado.security.dto.ProductoTO;
import com.mercado.security.entity.Guia;
import com.mercado.security.entity.Producto;
import com.mercado.security.repository.GuiaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/guias")
@CrossOrigin
public class GuiaController {
    @Autowired
    GuiaRepository guiaRepository;
    @PreAuthorize("hasAuthority('READ_ALL_GUIAS')")

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
    @PreAuthorize("hasAuthority('SAVE_ONE_GUIA')")

    @PostMapping
    public ResponseEntity<Guia>createOne(@RequestBody @Valid Guia guia){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.guiaRepository.save(guia)
        );
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_GUIA')")

    @PostMapping(path = "save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guia>guardarGuia(@RequestBody Guia guia){
        guia.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.guiaRepository.save(guia)
        );
    }
    @PreAuthorize("hasAuthority('READ_ALL_GUIAS')")

    @PostMapping(path = "buscarAD")
    public ResponseEntity <List<Producto>>buscarPorFechasNombre(@RequestBody  @Valid ProductoTO productos){
        List<Guia> listaGuias=guiaRepository.findGuiasByFechas(productos.getFechaInicio(), productos.getFechaFin());
        List<Producto> listaFinal=new ArrayList<>();

        if (productos.getNombre()==null){
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>>handlerGenericException(Exception exception, HttpServletRequest httpServletRequest){
        Map<String,String>apiError=new HashMap<>();
        apiError.put("message",exception.getLocalizedMessage());
        apiError.put("timestamp",new Date().toString());
        apiError.put("url",httpServletRequest.getRequestURL().toString());
        apiError.put("http-method",httpServletRequest.getMethod());
        HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
        if(exception instanceof AccessDeniedException){
            status=HttpStatus.FORBIDDEN;
        }
        return ResponseEntity.status(status).body(apiError);
    }




}
