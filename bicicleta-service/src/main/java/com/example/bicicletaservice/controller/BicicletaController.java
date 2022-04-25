package com.example.bicicletaservice.controller;

import com.example.bicicletaservice.entidades.Bicicleta;
import com.example.bicicletaservice.servicios.BicicletaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bicicleta")
public class BicicletaController {

    private final BicicletaServices bicicletaServices;

    public BicicletaController(BicicletaServices bicicletaServices) {
        this.bicicletaServices = bicicletaServices;
    }

    @GetMapping("")
    public ResponseEntity<List<Bicicleta>> listarBicicletas(){
        List<Bicicleta> bicicletaList=bicicletaServices.getAll();
        if (bicicletaList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bicicletaList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bicicleta> getBicicletaId(@PathVariable("id") int id){
        Bicicleta bicicleta=bicicletaServices.getBicicletaId(id);
        if (bicicleta==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bicicleta);
    }
    @PostMapping("")
    public ResponseEntity<Bicicleta> guardarBicicleta(@RequestBody Bicicleta bicicleta){
        Bicicleta nuevaBicicleta=bicicletaServices.saveBicicleta(bicicleta);
        return  ResponseEntity.ok(nuevaBicicleta);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Bicicleta>> getListaBicicletas(@PathVariable("usuarioId") int usuarioId){
        List<Bicicleta> bicicletaList=bicicletaServices.byUsuarioId(usuarioId);
        if (bicicletaList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bicicletaList);
    }

}
