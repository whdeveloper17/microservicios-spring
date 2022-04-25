package com.example.carroservice.servicio;

import com.example.carroservice.entidades.Carro;
import com.example.carroservice.repositorio.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private CarroRepository carroRepository;
    public CarroService(CarroRepository carroRepository){
        this.carroRepository=carroRepository;
    }

        public List<Carro> getAll(){
        return  carroRepository.findAll();
    }

    public Carro getCarroById(int id){
        return  carroRepository.findById(id).orElse(null);
    }

    public Carro guardarCarro(Carro carro){
        Carro nuevoCarro=carroRepository.save(carro);
        return  nuevoCarro;
    }

    public List<Carro> byUsuarioId(int usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }
}
