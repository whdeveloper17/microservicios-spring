package com.example.bicicletaservice.servicios;

import com.example.bicicletaservice.entidades.Bicicleta;
import com.example.bicicletaservice.repositorio.BicicletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicicletaServices {

    private final BicicletaRepository bicicletaRepository;

    public BicicletaServices(BicicletaRepository bicicletaRepository) {
        this.bicicletaRepository = bicicletaRepository;
    }

    public List<Bicicleta> getAll(){
        return bicicletaRepository.findAll();
    }
    public Bicicleta getBicicletaId(int id){
        return bicicletaRepository.findById(id).orElse(null);
    }
    public Bicicleta saveBicicleta(Bicicleta bicicleta){
        return  bicicletaRepository.save(bicicleta);
    }
    public List<Bicicleta> byUsuarioId(int usuarioId){
        return bicicletaRepository.findByUsuarioId(usuarioId);
    }
}
