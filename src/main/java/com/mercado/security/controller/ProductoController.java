package com.mercado.security.controller;

import com.mercado.security.entity.Producto;
import com.mercado.security.entity.Usuario;
import com.mercado.security.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<Producto>>buscarProductos(){
        System.out.println("Hola");
        List<Producto> listaProductos=productoRepository.findAll();
        return ResponseEntity.ok(listaProductos);
    }

    @GetMapping(path = "/{nombre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<Producto>>buscarProducto(@PathVariable String nombre){
        System.out.println("Hola 2");
        List<Producto> listaProductos=productoRepository.findAll();

        List<Producto> filtro = new ArrayList<>();

        for (Producto product : listaProductos) {
            if (product.getNombre().contains(nombre)) {
                filtro.add(product);
            }
        }
        return ResponseEntity.ok(filtro);
    }


}
