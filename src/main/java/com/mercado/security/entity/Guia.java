package com.mercado.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "guia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guia {

    @Id
    private String id;

    private String numero;
    private LocalDateTime fecha;

    @DBRef
    private List<Producto> productos;

}
