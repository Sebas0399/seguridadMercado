package com.mercado.security.controller;

import com.mercado.security.entity.Producto;
import com.mercado.security.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {

    @Autowired
    public ProductoRepository productoRepository;

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

}
