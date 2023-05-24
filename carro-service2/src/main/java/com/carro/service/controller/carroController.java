package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entidades.Carro;
import com.carro.service.service.CarroService;


@RestController
@RequestMapping("/carro")
public class carroController {
	
	@Autowired
	private CarroService CarroServicee;
	
		
	@GetMapping("/leerCarro")
		public ResponseEntity<List<Carro>> ListarCarro(){
		
		List<Carro> listaCarros= CarroServicee.getAll();
		
		if (listaCarros.isEmpty()) {
			
			return ResponseEntity.noContent().build();//noContent :cuando la lista no tiene contenido
		}else {
			
			return ResponseEntity.ok(listaCarros);
		}
		
	}
	
	
	
	@GetMapping("/carroid/{id}")
	public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") int id){
		
		Carro carro= CarroServicee.carroByid(id);
		
		if(carro==null) {
			
			return ResponseEntity.notFound().build(); // se usa notFond cuando no se encontro el objeto
		}else {
			return ResponseEntity.ok(carro);
			
		}
		
		
	
	}
	
	
	@PostMapping("/guardar")
	public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){
		
		Carro nuevoucarro=CarroServicee.save(carro);
		return ResponseEntity.ok(nuevoucarro);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> ListarCarroPorIDUsuario(@PathVariable("usuarioId") int usuarioId){
	
	List<Carro> listaCarros= CarroServicee.byUsuarioId(usuarioId);
	
	if (listaCarros.isEmpty()) {
		
		return ResponseEntity.noContent().build();//noContent :cuando la lista no tiene contenido
	}else {
		
		return ResponseEntity.ok(listaCarros);
	}
	
}

	
}
