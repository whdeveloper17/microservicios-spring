package com.example.carroservice.entidades;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marca;
    private String modelo;
    private int usuarioId;


}
