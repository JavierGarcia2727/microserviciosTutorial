package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.moto.service.entidades.Moto;
import com.moto.service.service.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService MotoService;
	
	
	@GetMapping("/Listamotos")
	public ResponseEntity<List<Moto>> ListaMotos(){
		
		List<Moto>ListaMotos=MotoService.listamoto();
		
		if (ListaMotos.isEmpty()) {
			
			return ResponseEntity.noContent().build();//noContent :cuando la lista no tiene contenido
		}else {
			
			return ResponseEntity.ok(ListaMotos);
		}
		
	}
	
	@GetMapping("/MotoPorId/{id}")
public ResponseEntity<Moto> moto(@PathVariable("id") int id){
		
		Moto moto=MotoService.MotoId(id);
		
		if (moto==null) {
			
			return ResponseEntity.notFound().build(); // se usa notFond cuando no se encontro el objeto
			
		}else {
			return ResponseEntity.ok(moto);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Moto> save(@RequestBody Moto moto){
		
		Moto guardarMoto=MotoService.save(moto);
		
		return ResponseEntity.ok(guardarMoto);
	}
	
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> ListarMotoPorIDUsuario(@PathVariable("usuarioId") int usuarioId){
	
	List<Moto> listaMotos= MotoService.ListaMotoPorIdUsuario(usuarioId);
	
	if (listaMotos.isEmpty()) {
		
		return ResponseEntity.noContent().build();//noContent :cuando la lista no tiene contenido
	}else {
		
		return ResponseEntity.ok(listaMotos);
	}
	
}
	
	
	
	

}
