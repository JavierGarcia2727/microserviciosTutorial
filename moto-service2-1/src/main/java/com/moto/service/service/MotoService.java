package com.moto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.moto.service.entidades.Moto;
import com.moto.service.repositorio.MotoRepositori;

@Service
public class MotoService {
	
	@Autowired

	private MotoRepositori MotoRepositoro;
	
	
	public List<Moto> listamoto() {
		
		return MotoRepositoro.findAll();
	}
	
	
	public Moto MotoId(int id) {
		
		return MotoRepositoro.findById(id).orElse(null);
	}
	
	public Moto save(Moto moto) {
	
	Moto nuevaMoto= MotoRepositoro.save(moto);

	return nuevaMoto;
	}
	
	public List<Moto> ListaMotoPorIdUsuario(int id_Usuario){
		
		return MotoRepositoro.findByUsuarioId(id_Usuario);
	}
	
	
}
