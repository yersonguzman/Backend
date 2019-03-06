package com.parqueadero.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.Exceptions.NoHayRegistro;
import com.parqueadero.constants.Constants;
import com.parqueadero.models.Registro;
import com.parqueadero.repositories.RegistroRepository;

@Service
public class RegistroServiceImp implements RegistroService {

	@Autowired
	private RegistroRepository registroRepository;

	@Override
	public Registro crearRegistro(Registro registro) {
		return registroRepository.save(registro);
	}

	@Override
	public Registro consultarRegistro(Long idRegistro) {
		Optional<Registro> registro = registroRepository.findById(idRegistro);
		if (!registro.isPresent()) {
			throw new NoHayRegistro(Constants.no_hay_registro);
		}

		return registro.get();
	}

	@Override
	public Registro modificarRegistro(Registro registro) {
		return registroRepository.save(registro);
	}

	@Override
	public void eliminarRegistro(Long idRegistro) {
		registroRepository.deleteById(idRegistro);

	}

	@Override
	public List<Registro> listarRegistros() {
		return registroRepository.findAll();
	}
	
	@Override
	public List<Registro> ListarRegistroPorPlaca(){
		return null;
	}

}
