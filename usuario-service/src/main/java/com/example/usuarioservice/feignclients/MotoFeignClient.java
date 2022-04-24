package com.example.usuarioservice.feignclients;

import com.example.usuarioservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service")
@RequestMapping("moto")
public interface MotoFeignClient {

    @PostMapping("")
     Moto save(@RequestBody Moto moto);

    @GetMapping("usuario/{usuarioId}")
     List<Moto> getMotos(@PathVariable("usuarioId") int usuarioId);
}
