package com.usuario.service.feignCleint;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.usuario.service.modelos.Moto;

@FeignClient(name="moto-service", url = "http://localhost:8082")
public interface MotoFeignClient {
	
	@PostMapping("/moto/save")
	public Moto saveMotoFeign(@RequestBody Moto moto);
	
	
	@GetMapping("/moto/usuario/{usuarioId}")
	public List<Moto> getMotoFeign(@PathVariable("usuarioId") int usuarioId);
	
}
