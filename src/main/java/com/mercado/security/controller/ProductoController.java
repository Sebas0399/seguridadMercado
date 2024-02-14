package com.mercado.security.controller;

import com.mercado.security.entity.Producto;
import com.mercado.security.entity.Usuario;
import com.mercado.security.repository.ProductoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {

    @Autowired
    public ProductoRepository productoRepository;

    @PreAuthorize("hasAuthority('SAVE_ALL_PRODUCTS')")
    @PostMapping
    public ResponseEntity<String> agregarProductos(@RequestBody List<Producto> productos) {
        try {
            // Guardar los productos en la base de datos
            this.productoRepository.saveAll(productos);
            return new ResponseEntity<>("Productos agregados exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al agregar productos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<Producto>>buscarProductos(){
        List<Producto> listaProductos=productoRepository.findAll();
        return ResponseEntity.ok(listaProductos);
    }
    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")

    @GetMapping(path = "/{nombre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<Producto>>buscarProducto(@PathVariable String nombre){
        Optional<List<Producto>>listaFiltrada=this.productoRepository.findByNombreContainingIgnoreCase(nombre);
        System.out.println(listaFiltrada.get());
        if(listaFiltrada.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(listaFiltrada.get());

        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }

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
