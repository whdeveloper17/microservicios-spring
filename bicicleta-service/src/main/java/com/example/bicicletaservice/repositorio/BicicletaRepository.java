package com.example.bicicletaservice.repositorio;

import com.example.bicicletaservice.entidades.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BicicletaRepository extends JpaRepository<Bicicleta,Integer> {

    List<Bicicleta> findByUsuarioId(int usuarioId);
}
