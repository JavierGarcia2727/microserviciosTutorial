package com.moto.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moto.service.entidades.Moto;

@Repository
public interface MotoRepositori extends JpaRepository<Moto, Integer>{
	
	public List<Moto> findByUsuarioId(int usuarioId);//debo colocar findBy mas la columna de la tabla escrita igual



}
