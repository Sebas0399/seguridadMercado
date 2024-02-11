package com.mercado.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    private String id;
    private String nombre;
    private String codigo;

    private int cantidad = 1;
    private String presentacion;
}
