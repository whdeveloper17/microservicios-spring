package com.example.usuarioservice.controller;

import com.example.usuarioservice.entidades.Usuario;
import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import com.example.usuarioservice.servicio.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;
    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @GetMapping("")
    public ResponseEntity<List<Usuario>> listUsuario(){
        List<Usuario> usuarios=usuarioService.getAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
        Usuario usuario=usuarioService.getUsuarioById(id);
        if (usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping("")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario=usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if (usuario==null){
            return ResponseEntity.noContent().build();
        }
        List<Carro> carroList=usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carroList);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if (usuario==null){
            return ResponseEntity.noContent().build();
        }
        List<Moto> motoList=usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motoList);
    }

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId,@RequestBody Carro carro){
        Carro nuevoCarro=usuarioService.saveCarro(usuarioId,carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId,@RequestBody Moto moto){
        Moto nuevaMoto=usuarioService.saveMoto(usuarioId,moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String,Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuariosId){
        Map<String,Object> resultado=usuarioService.getUsuarioAndVehiculos(usuariosId);
        return ResponseEntity.ok(resultado);
    }
}
