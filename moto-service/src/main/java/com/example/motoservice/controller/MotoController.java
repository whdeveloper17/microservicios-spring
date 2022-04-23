package com.example.motoservice.controller;

import com.example.motoservice.entidades.Moto;
import com.example.motoservice.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/moto")
public class MotoController {

    private final MotoService motoService;
    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping("")
    public ResponseEntity<List<Moto>> listarMoto(){
        List<Moto> motoList=motoService.getAll();
        if (motoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getIdMoto(@PathVariable("id") int id){
        Moto moto=motoService.getMotoById(id);
        if (moto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @PostMapping("")
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto){
        Moto nuevoCarro=motoService.save(moto);
        return ResponseEntity.ok(nuevoCarro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> getListaMotoUsuario(@PathVariable("usuarioId") int usuarioId){
        List<Moto> motoList=motoService.byUsuarioId(usuarioId);
        if (motoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motoList);

    }
}
