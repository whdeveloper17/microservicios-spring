package com.example.usuarioservice.controller;

import com.example.usuarioservice.entidades.Usuario;
import com.example.usuarioservice.modelos.Bicicleta;
import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import com.example.usuarioservice.servicio.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
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

    @CircuitBreaker(name = "carrosCB",fallbackMethod = "fallBackGetCarros")
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if (usuario==null){
            return ResponseEntity.noContent().build();
        }
        List<Carro> carroList=usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carroList);
    }

    @CircuitBreaker(name = "motosCB",fallbackMethod = "fallBackGetMotos")
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if (usuario==null){
            return ResponseEntity.noContent().build();
        }
        List<Moto> motoList=usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motoList);
    }

    @CircuitBreaker(name = "bicicletasCB",fallbackMethod = "fallBackGetBicicletas")
    @GetMapping("/bicicleta/{usuarioId}")
    public ResponseEntity<List<Bicicleta>> getBicicletas(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if (usuario==null){
            return ResponseEntity.noContent().build();
        }
        List<Bicicleta> bicicletaList=usuarioService.getBicicletas(usuarioId);
        return ResponseEntity.ok(bicicletaList);
    }

    @CircuitBreaker(name = "carrosCB",fallbackMethod = "fallBackSaveCarros")
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId,@RequestBody Carro carro){
        Carro nuevoCarro=usuarioService.saveCarro(usuarioId,carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @CircuitBreaker(name = "motosCB",fallbackMethod = "fallBackSaveMotos")
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId,@RequestBody Moto moto){
        Moto nuevaMoto=usuarioService.saveMoto(usuarioId,moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @CircuitBreaker(name = "bicicletasCB",fallbackMethod = "fallBackSaveBicicletas")
    @PostMapping("/bicicleta/{usuarioId}")
    public ResponseEntity<Bicicleta> guardarMBicicleta(@PathVariable("usuarioId") int usuarioId,@RequestBody Bicicleta bicicleta){
        Bicicleta nuevaBicicleta=usuarioService.saveBicicleta(usuarioId,bicicleta);
        return ResponseEntity.ok(nuevaBicicleta);
    }

    @CircuitBreaker(name = "todosCB",fallbackMethod = "fallBackGetTodos")
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String,Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuariosId){
        Map<String,Object> resultado=usuarioService.getUsuarioAndVehiculos(usuariosId);
        return ResponseEntity.ok(resultado);
    }

    private ResponseEntity<List<Carro>>  fallBackGetCarros(@PathVariable("usuarioId") int usuarioId,RuntimeException exception){
        return new ResponseEntity("El usuario : "+usuarioId+" tiene los carros en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Carro>  fallBackSaveCarros(@PathVariable("usuarioId") int usuarioId,@RequestBody Carro carro,RuntimeException exception){
        return new ResponseEntity("El usuario : "+usuarioId+" no  tiene dinero para los carros", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>>  fallBackGetMotos(@PathVariable("usuarioId") int usuarioId,RuntimeException exception){
        return new ResponseEntity("El usuario : "+usuarioId+" tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Moto>  fallBackSaveMotos(@PathVariable("usuarioId") int usuarioId,@RequestBody Moto moto,RuntimeException exception){
        return new ResponseEntity("El usuario : "+usuarioId+" no  tiene dinero para la moto", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>>  fallBackGetBicicletas(@PathVariable("usuarioId") int usuarioId,RuntimeException exception){
        return new ResponseEntity("El usuario : "+usuarioId+" tiene las bicicletas en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Moto>  fallBackSaveBicicletas(@PathVariable("usuarioId") int usuarioId,@RequestBody Bicicleta bicicleta,RuntimeException exception){
        return new ResponseEntity("El usuario : "+usuarioId+" no  tiene dinero para la bicicleta", HttpStatus.OK);
    }
    private ResponseEntity<Map<String,Object>>  fallBackGetTodos(@PathVariable("usuarioId") int usuarioId,RuntimeException exception){
        return new ResponseEntity("El usuario : "+usuarioId+" tiene los vehiculos en el taller", HttpStatus.OK);
    }
}
