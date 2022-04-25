package com.example.usuarioservice.feignclients;

import com.example.usuarioservice.modelos.Bicicleta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bicicleta-service")
@RequestMapping("/bicicleta")
public interface BicicletasFeignClient {
    @PostMapping
    public Bicicleta save(@RequestBody Bicicleta bicicleta);

    @GetMapping("/usuario/{usuarioId}")
    public List<Bicicleta> getBicicletas(@PathVariable("usuarioId")int usuarioId);
}
