package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignCleint.CarroFeignClient;
import com.usuario.service.feignCleint.MotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioServicvio {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CarroFeignClient CarroFeignCliente;

	@Autowired
	private MotoFeignClient MotoFeignCliente;
	// Proximos dos metodos son usados para Rest Template

	public List<Carro> getCarros(int usuarioId) {

		List<Carro> ListaCarro = restTemplate.getForObject("http://localhost:8081/carro/usuario/" + usuarioId,
				List.class);

		return ListaCarro;
	}

	public List<Moto> getMotos(int usuarioId) {

		List<Moto> ListaMoto = restTemplate.getForObject("http://localhost:8082/moto/usuario/" + usuarioId, List.class);

		return ListaMoto;
	}

	// Proxima es usada en Feign

	public Carro saveCarroFeign(int usuarioID, Carro carro) {

		carro.setUsuarioId(usuarioID);
		Carro nuevoCarro = CarroFeignCliente.saveCarroFeign(carro);
		return nuevoCarro;

	}

	public Moto saveMotoFeign(int usuarioID, Moto moto) {

		moto.setUsuarioId(usuarioID);
		Moto nuevoMoto = MotoFeignCliente.saveMotoFeign(moto);
		return nuevoMoto;

	}
	// retorna anvos moto y carro con feign

	public Map<String, Object> getUsuarioAdnVehiculos(int usuarioId) {
		System.out.println("----------------------------------- 3 --------------------------------");
		Map<String, Object> resultado = new HashMap<>();
		System.out.println("----------------------------------- 3.1 --------------------------------");
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		System.out.println("----------------------------------- 3.2 --------------------------------");
		if (usuario == null) {
			System.out.println("----------------------------------- 3.3 --------------------------------");
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		} else {
			
			System.out.println("----------------------------------- 4 ----------------- "+usuario);
			
			resultado.put("Usuario", usuario);
			System.out.println("----------------------------------- 4.1 ----------------- ");
			List<Carro> carros = CarroFeignCliente.getCarroFeign(usuarioId);
			System.out.println("----------------------------------- 4.2 ------- CARROS ---------- "+carros);
			if (carros== null) {
				System.out.println("----------------------------------- 5 --------------------------------");
				resultado.put("Carros", "El usuario no tiene Carros");

			} else {
				System.out.println("----------------------------------- 6 --------------------------------");
				resultado.put("Carros", carros);
			}
/////////////////////////////////
			List<Moto> motos = MotoFeignCliente.getMotoFeign(usuarioId);
			System.out.println("----------------------------------- 6 ---------- MOTOS ---------------------- "+motos);
			if (motos== null) {
				resultado.put("Motos", "El usuario no tiene Motos");
				System.out.println("----------------------------------- 6 ----------  ---------------------- ");
			} else {
				System.out.println("----------------------------------- 7 --------------------------------");
				resultado.put("Motos", motos);
			}
		}

		return resultado;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<Usuario> getAll() {

		return usuarioRepository.findAll();
	}

	public Usuario usuarioByid(int id) {
		return usuarioRepository.findById(id).orElse(null);// si no quisiera usar orElse deberia retornar un tipo
															// optional<Persona>
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevousuario = usuarioRepository.save(usuario);
		return nuevousuario;

	}

}
