package com.usuario.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.service.UsuarioServicvio;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServicvio UsuarioServicvios;
	
		
	@GetMapping("/leerUsuarios")
		public ResponseEntity<List<Usuario>> ListarUsuarios(){
		
		List<Usuario> listaUsuarios= UsuarioServicvios.getAll();
		
		if (listaUsuarios.isEmpty()) {
			
			return ResponseEntity.noContent().build();//noContent :cuando la lista no tiene contenido
		}else {
			
			return ResponseEntity.ok(listaUsuarios);
		}
		
	}
	
	
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		
		Usuario usuario= UsuarioServicvios.usuarioByid(id);
		
		if(usuario==null) {
			
			return ResponseEntity.notFound().build(); // se usa notFond cuando no se encontro el objeto
		}else {
			return ResponseEntity.ok(usuario);
			
		}
	
	}
	
	
	@PostMapping("/guardar")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		
		Usuario nuevousuario=UsuarioServicvios.save(usuario);
		return ResponseEntity.ok(nuevousuario);
	}
	
	
	
	
	
	
	@GetMapping("/ListaCarros/{usuarioId}")
	public ResponseEntity<List<Carro>> ListaCarros(@PathVariable("usuarioId") int usuarioId){
		
		Usuario usuario=UsuarioServicvios.usuarioByid(usuarioId);
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}else {
			
			
			List<Carro> ListaCarros=UsuarioServicvios.getCarros(usuarioId);
			return ResponseEntity.ok(ListaCarros);
		}
		
	}
	
	
	
	
	@GetMapping("/ListaMotos/{usuarioId}")
	public ResponseEntity<List<Moto>> ListaMotos(@PathVariable("usuarioId") int usuarioId){
		
		Usuario usuario=UsuarioServicvios.usuarioByid(usuarioId);
		System.out.println("vino a lista moto");
		if (usuario==null) {
			System.out.println("vino a lista moto 1");
			return ResponseEntity.notFound().build();
		}else {
			System.out.println("vino a lista moto 2");
			
			List<Moto> ListaMotos=UsuarioServicvios.getMotos(usuarioId);
			return ResponseEntity.ok(ListaMotos);
		}
		
	}
	
		//metodo guardar con Feign
	
	
	
	

	@PostMapping("/guardarCarroFeign/{usuarioID}")
	public ResponseEntity<Carro> guardarCarroFeign(@PathVariable("usuarioID") int usuarioID,  @RequestBody Carro carro){
		
		Carro nuevoCarroFeign=UsuarioServicvios.saveCarroFeign(usuarioID, carro);
		return ResponseEntity.ok(nuevoCarroFeign);
	}
	
	
	
	
	

	@PostMapping("/guardarMotoFeign/{usuarioID}")
	public ResponseEntity<Moto> guardarMotoFeign(@PathVariable("usuarioID") int usuarioID,  @RequestBody Moto moto){
		
		Moto nuevoMotoFeign=UsuarioServicvios.saveMotoFeign(usuarioID, moto);
		return ResponseEntity.ok(nuevoMotoFeign);
	}
	
	
	//////proximo metodo llama todo sea carro y motos y usuario con Map<String, object>
	
	
	@GetMapping("/ListarTodosVeiculos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> ListarTodosVehiculos(@PathVariable("usuarioId") int usuarioId){
		System.out.println("-------------------------------------------------------------------");
		Map<String, Object> resultado=UsuarioServicvios.getUsuarioAdnVehiculos(usuarioId);
		
		System.out.println("----------------------------------- 2 --------------------------------");
		
		return ResponseEntity.ok(resultado);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
