package com.mercado.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosTO implements Serializable{

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String nombre;
}
