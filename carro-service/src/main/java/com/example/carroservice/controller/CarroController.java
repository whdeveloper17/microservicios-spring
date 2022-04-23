package com.example.carroservice.controller;

import com.example.carroservice.entidades.Carro;
import com.example.carroservice.servicio.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private CarroService carroService;

    public CarroController(CarroService carroService){
        this.carroService=carroService;
    }


    @GetMapping("")
    public ResponseEntity<List<Carro>> listarCarro(){
       List<Carro> carroList=carroService.getAll();
       if (carroList.isEmpty()){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(carroList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getIdCarro(@PathVariable("id") int id){
        Carro carro=carroService.getCarroById(id);
        if (carro==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @PostMapping("")
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){
        Carro nuevoCarro=carroService.guardarCarro(carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> getListaCarroUsuario(@PathVariable("usuarioId") int usuarioId){
        List<Carro> carroList=carroService.byUsuarioId(usuarioId);
        if (carroList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carroList);

    }
}
