package com.parqueadero.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.models.Registro;
import com.parqueadero.services.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController {

	@Autowired
	private RegistroService registroService;

	@PostMapping
	public Registro crearRegistro(@RequestBody Registro registro) {
		return registroService.crearRegistro(registro);
	}

	@GetMapping("/{idRegistro}")
	public ResponseEntity<Registro> consultarRegistro(@PathVariable Long idRegistro) {
		return ResponseEntity.ok(registroService.consultarRegistro(idRegistro));
	}

	@GetMapping
	public ResponseEntity<List<Registro>> listarRegistros() {
		return ResponseEntity.ok(registroService.listarRegistros());
	}

	@PutMapping
	public Registro modificarRegistro(@RequestBody Registro registro) {
		return registroService.modificarRegistro(registro);
	}

	@DeleteMapping("/{idRegistro}")
	public void eliminarRegistro(@PathVariable Long idRegistro) {
		registroService.eliminarRegistro(idRegistro);
	}
	
	@GetMapping("/{placa}")
	public List<Registro> listarRegistroPorPlaca(@PathVariable String placa){
		return null;
		
	}

}
