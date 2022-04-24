package com.example.usuarioservice.feignclients;

import com.example.usuarioservice.modelos.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-service")
@RequestMapping("/carro")
public interface CarroFeignClient {

    @PostMapping
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable("usuarioId")int usuarioId);
}
