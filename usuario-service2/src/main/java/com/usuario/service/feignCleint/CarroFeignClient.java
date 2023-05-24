package com.usuario.service.feignCleint;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;

@FeignClient(name="carro-service", url = "http://localhost:8081")
public interface CarroFeignClient {
	
	@PostMapping("/carro/guardar")
	public Carro saveCarroFeign(@RequestBody Carro carro);
	
	@GetMapping("/carro/usuario/{usuarioId}")
	public List<Carro> getCarroFeign(@PathVariable("usuarioId") int usuarioId);

}
