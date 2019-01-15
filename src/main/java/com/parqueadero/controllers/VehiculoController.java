package com.parqueadero.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.models.Vehiculo;
import com.parqueadero.services.VehiculoService;
import com.parqueadero.tarifas.PagoSalidaVehiculo;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

	private VehiculoService vehiculoService;

	public VehiculoController(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	@PostMapping
	public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo) {
		return vehiculoService.crearVehiculo(vehiculo);
	}

	@GetMapping("/{placa}")
	public Optional<Vehiculo> consultarVehiculo(@PathVariable String placa) {
		return vehiculoService.consultarVehiculo(placa);
	}

	@GetMapping
	public ResponseEntity<List<Vehiculo>> listarVehiculos() {
		return ResponseEntity.ok(vehiculoService.listarVehiculos());
	}

	@PutMapping
	public Vehiculo modificarVehiculo(@RequestBody Vehiculo vehiculo) {
		return vehiculoService.modificarVehiculo(vehiculo);
	}

	@DeleteMapping("/{placa}")
	public void eliminarVehiculo(@PathVariable String placa) {
		vehiculoService.eliminarVehiculo(placa);
	}

	@PutMapping("/salir/{placa}")
	public ResponseEntity<PagoSalidaVehiculo> salirDelParqueadero(@PathVariable String placa) {
		return ResponseEntity.ok(vehiculoService.salirDelParqueadero(placa));

	}
	
}
