package com.example.motoservice.service;

import com.example.motoservice.entidades.Moto;
import com.example.motoservice.repositorio.MotoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    private final MotoRespository motoRespository;

    public MotoService(MotoRespository motoRespository) {
        this.motoRespository = motoRespository;
    }


    public List<Moto> getAll(){
        return  motoRespository.findAll();
    }

    public Moto getMotoById(int id){
        return motoRespository.findById(id).orElse(null);
    }

    public Moto save(Moto moto){
        Moto nuevaMoto=motoRespository.save(moto);
        return nuevaMoto;
    }
    public List<Moto> byUsuarioId(int usuarioId){
        return motoRespository.findByUsuarioId(usuarioId);
    }
}
