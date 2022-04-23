package com.example.usuarioservice.servicio;

import com.example.usuarioservice.configuracion.RestTemplateConfig;
import com.example.usuarioservice.entidades.Usuario;
import com.example.usuarioservice.feignclients.CarroFeignClient;
import com.example.usuarioservice.feignclients.MotoFeignClient;
import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import com.example.usuarioservice.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    private final RestTemplate restTemplate;
    private final UsuarioRepository usuarioRepository;
    private final CarroFeignClient carroFeignClient;
    private final MotoFeignClient motoFeignClient;

    public UsuarioService(UsuarioRepository usuarioRepository, RestTemplate restTemplate, CarroFeignClient carroFeignClient, MotoFeignClient motoFeignClient) {
        this.usuarioRepository = usuarioRepository;
        this.restTemplate = restTemplate;
        this.carroFeignClient = carroFeignClient;
        this.motoFeignClient = motoFeignClient;
    }

    public List<Carro> getCarros(int usuarioId){
        List<Carro> carroList=restTemplate.getForObject("http://localhost:8002/carro/usuario/"+usuarioId,List.class);
        return carroList;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motoList=restTemplate.getForObject("http://localhost:8003/moto/usuario/"+usuarioId,List.class);
        return motoList;
    }

    public Carro saveCarro(int usuarioId,Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro=carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId,Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevoMoto=motoFeignClient.save(moto);
        return nuevoMoto;
    }
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario=usuarioRepository.save(usuario);
        return nuevoUsuario;
    }

    public Map<String,Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String,Object> resultado=new HashMap<>();
        Usuario usuario=usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario ==null){
            resultado.put("Mensaje","El usuario no existe");
            return resultado;
        }
        resultado.put("usuario",usuario);
        List<Carro> carroList=carroFeignClient.getCarros(usuarioId);
        if (carroList.isEmpty()){
            resultado.put("carros","El usuario no tiene carros");
        }else{
            resultado.put("carros",carroList);
        }

        List<Moto> motoList=motoFeignClient.getMotos(usuarioId);
        if (motoList.isEmpty()){
            resultado.put("motos","El usuario no tiene motos");
        }else {
            resultado.put("motos",motoList);
        }
        return resultado;
    }
}
