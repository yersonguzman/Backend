package com.parqueadero.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.Exceptions.DiaNoPermitido;
import com.parqueadero.Exceptions.NoseEncuentraRegistro;
import com.parqueadero.Exceptions.NoseEncuentraVehiculo;
import com.parqueadero.Exceptions.ParqueoLleno;
import com.parqueadero.constants.Constants;
import com.parqueadero.models.Registro;
import com.parqueadero.models.Vehiculo;
import com.parqueadero.repositories.RegistroRepository;
import com.parqueadero.repositories.VehiculoRepository;
import com.parqueadero.tarifas.PagoSalidaVehiculo;

@Service
public class VehiculoServiceImp implements VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Autowired
	private RegistroRepository registroRepository;

	@Override
	public Vehiculo crearVehiculo(Vehiculo vehiculo) {

		boolean espacioParqueo = CapacidadEstacionamiento(vehiculo);
		if (!espacioParqueo) {
			throw new ParqueoLleno(Constants.sin_espacio);
		}

		boolean restriccionPlacaA = ValidarPlaca(vehiculo.getPlaca());
		if (restriccionPlacaA) {
			boolean diaValido = IngresarDia();
			if (!diaValido) {
				throw new DiaNoPermitido(Constants.dia_no_permitido);
			}
		}

		boolean vehiculoYaExiste = verificarCarroExistente(vehiculo.getPlaca());

		if (vehiculoYaExiste) {
			throw new NoseEncuentraVehiculo(Constants.vehiculo_parqueado);
		}

		vehiculo.setFechaIngreso(LocalDateTime.now());
		vehiculoRepository.save(vehiculo);

		Registro registro = new Registro();
		registro.setPlaca(vehiculo.getPlaca());
		registro.setFechaIngreso(vehiculo.getFechaIngreso());
		registroRepository.save(registro);

		return vehiculo;
	}

	@Override
	public Optional<Vehiculo> consultarVehiculo(String placa) {
		return vehiculoRepository.findById(placa);
	}

	@Override
	public Vehiculo modificarVehiculo(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public void eliminarVehiculo(String placa) {
		vehiculoRepository.deleteById(placa);

	}

	@Override
	public List<Vehiculo> listarVehiculos() {
		return vehiculoRepository.findAll();
	}

	public boolean CapacidadEstacionamiento(Vehiculo vehiculo) {
		boolean disponible = false;
		Integer cantidad = 0;

		switch (vehiculo.getTipo()) {

		case "carro":
			cantidad = vehiculoRepository.contarporClaseVehiculo(vehiculo.getTipo());
			if (cantidad < Constants.capacidad_carro)
				disponible = true;
			break;

		case "moto":
			cantidad = vehiculoRepository.contarporClaseVehiculo(vehiculo.getTipo());
			if (cantidad < Constants.capacidad_moto)
				disponible = true;
			break;

		default:
			break;
		}
		return disponible;
	}

	public boolean ValidarPlaca(String placa) {
		boolean PlacaA = false;
		if (placa.toUpperCase().startsWith(Constants.regla_de_placa)) {
			PlacaA = true;
		}
		return PlacaA;
	}

	public boolean IngresarDia() {
		boolean diaValido = false;
		LocalDate fechaActual = getfechaActual();
		if (fechaActual.getDayOfWeek().equals(DayOfWeek.SUNDAY)	|| fechaActual.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			diaValido = true;
		}
		return diaValido;
	}

	public boolean verificarCarroExistente(String placa) {
		boolean vehiculoYaParqueado = false;

		Optional<Vehiculo> vehiculo = vehiculoRepository.findById(placa);

		if (vehiculo.isPresent()) {

			vehiculoYaParqueado = true;
		}
		return vehiculoYaParqueado;
	}

	public LocalDate getfechaActual() {
		return LocalDate.now();
	}

	public Integer ObtenerHorasDeParqueo(Registro registro) {
		Integer horasParqueo = 0;
		LocalDateTime fIngreso = registro.getFechaIngreso();
		LocalDateTime fSalida = registro.getFechaSalida();
		Duration duracion = Duration.between(fIngreso, fSalida);
		Long minutosParqueo = duracion.toMinutes();
		Double tiempoParqueo = minutosParqueo / 60D;
		horasParqueo = tiempoParqueo.intValue();

		if (tiempoParqueo > horasParqueo) {
			horasParqueo += 1;
		}

		return horasParqueo;
	}

	@Override
	public PagoSalidaVehiculo salirDelParqueadero(String placa) {
		BigDecimal totalPagar = BigDecimal.ZERO;

		try {
			Optional<Vehiculo> vehiculo = vehiculoRepository.findById(placa);
			if (!vehiculo.isPresent()) {
				throw new NoseEncuentraVehiculo(Constants.no_hay_carro);
			}
			List<Registro> listaRegistro = registroRepository
					.findtop1ByPlacaOrderByFechaIngresoDesc(vehiculo.get().getPlaca())
					.orElseThrow(() -> new NoseEncuentraRegistro(Constants.no_hay_DatoRegistro));

			Registro registro = listaRegistro.get(0);

			registro.setFechaSalida(LocalDateTime.now());
			Integer horasTranscurridas = ObtenerHorasDeParqueo(registro);

			switch (vehiculo.get().getTipo()) {
			case "carro":

				if (horasTranscurridas < Constants.dia_comienza) {
					totalPagar = Constants.valor_hora_carro.multiply(new BigDecimal(horasTranscurridas));

				} else if (horasTranscurridas >= Constants.dia_comienza
						&& horasTranscurridas <= Constants.dia_termina) {
					totalPagar = Constants.valor_dia_carro;
				} else {

					BigDecimal cantidadDias = new BigDecimal(horasTranscurridas).divide(new BigDecimal(24), 0,
							RoundingMode.HALF_UP);
					BigDecimal cantidadHoras = new BigDecimal(horasTranscurridas).remainder(new BigDecimal(24));
					BigDecimal totalValorDias = cantidadDias.multiply(Constants.valor_dia_carro);
					BigDecimal totalValorHoras = cantidadHoras.multiply(Constants.valor_hora_carro);
					totalPagar = totalValorDias.add(totalValorHoras);
				}
				break;

			case "moto":
				if (horasTranscurridas >= Constants.dia_comienza && horasTranscurridas <= Constants.dia_termina) {
					totalPagar = Constants.valor_dia_moto;
				} else if (horasTranscurridas < Constants.dia_comienza) {
					totalPagar = Constants.valor_hora_moto.multiply(new BigDecimal(horasTranscurridas));
				} else {
					BigDecimal cantidadDias = new BigDecimal(horasTranscurridas).divide(new BigDecimal(24), 0,
							RoundingMode.HALF_UP);
					BigDecimal cantidadHoras = new BigDecimal(horasTranscurridas).remainder(new BigDecimal(24));
					BigDecimal totalValorDias = cantidadDias.multiply(Constants.valor_dia_moto);
					BigDecimal totalValorHoras = cantidadHoras.multiply(Constants.valor_hora_moto);
					totalPagar = totalValorDias.add(totalValorHoras);
				}

				if (vehiculo.get().getCilindraje() > Constants.alto_cilindraje) {
					totalPagar = totalPagar.add(Constants.excedente_alto_cilindraje);
				}

				break;

			default:
				break;
			}

			registro.setTotalPago(totalPagar);
			registroRepository.save(registro);

			eliminarVehiculo(placa);

			PagoSalidaVehiculo psv = new PagoSalidaVehiculo(totalPagar, placa, horasTranscurridas);
			return psv;

		} catch (Exception e) {
			return null;
		}

	}

}
