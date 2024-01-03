package com.mercado.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "provincias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {

    @Id
    private String id;
    private String codigo;
    private String nombre;

    @DBRef
    private List<Canton> cantones;
}
