package com.carro.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.service.entidades.Carro;
import com.carro.service.repositorio.CarroRepository;


@Service
public class CarroService {

	@Autowired
	private CarroRepository CarroRepositori;
	
	public List<Carro> getAll() {

		return CarroRepositori.findAll();
	}

	public Carro carroByid(int id) {
		return CarroRepositori.findById(id).orElse(null);// si no quisiera usar orElse deberia retornar un tipo
															// optional<Persona>
	}
	
	
	
	public Carro save(Carro carro) {
		Carro nuevocarro =CarroRepositori.save(carro);
		return nuevocarro;
		
	}
	
	public List<Carro> byUsuarioId(int usuarioID){
		
		return CarroRepositori.findByUsuarioId(usuarioID);
	}
	
}
