package com.parqueadero.pruebasUnitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.models.Vehiculo;
import com.parqueadero.modelsTest.VehiculoTestDataBuilder;
import com.parqueadero.repositories.VehiculoRepository;
import com.parqueadero.services.VehiculoServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoServiceTest {

	@InjectMocks
	VehiculoServiceImp vehiculoServiceImp;

	VehiculoServiceImp vehiculoServiceImpSpy = Mockito.spy(VehiculoServiceImp.class);

	@Mock
	private VehiculoRepository vehiculoRepository;

	
	

	@Test
	public void ValidarPlacaA() {
		// arrange
		String placa = "ABC201";
		// act
		Boolean noEsValido = vehiculoServiceImp.ValidarPlaca(placa);
		// assert
		assertTrue(noEsValido);

	}

	@Test
	public void ValidarPlaca() {
		// arrange
		String placa = "LAT201";
		// act
		Boolean noEsValido = vehiculoServiceImp.ValidarPlaca(placa);
		// assert
		assertFalse(noEsValido);

	}

	@Test
	public void probarConCapacidadEspacioCarros()  {
		// Arrange
		boolean hayCapacidad;
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo("carro").build();
		Mockito.when(vehiculoRepository.contarporClaseVehiculo(vehiculo.getTipo())).thenReturn(15);
		// Act
		hayCapacidad = vehiculoServiceImp.CapacidadEstacionamiento(vehiculo);
		// Assert
		assertEquals(true, hayCapacidad);
	}

	@Test
	public void probarSinCapacidadEspacioCarros()  {
		// Arrange
		boolean hayCapacidad;
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo("carro").build();
		Mockito.when(vehiculoRepository.contarporClaseVehiculo(vehiculo.getTipo())).thenReturn(20);
		// Act
		hayCapacidad = vehiculoServiceImp.CapacidadEstacionamiento(vehiculo);
		// Assert
		assertEquals(false, hayCapacidad);
	}

	@Test
	public void probarConCapacidadEspacioMoto()  {
		// Arrange
		boolean hayCapacidad;
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo("moto").build();
		Mockito.when(vehiculoRepository.contarporClaseVehiculo(vehiculo.getTipo())).thenReturn(4);
		// Act
		hayCapacidad = vehiculoServiceImp.CapacidadEstacionamiento(vehiculo);
		// Assert
		assertEquals(true, hayCapacidad);
	}

	@Test
	public void probarSinCapacidadEspacioMoto()  {
		// Arrange
		boolean hayCapacidad;
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo("moto").build();
		Mockito.when(vehiculoRepository.contarporClaseVehiculo(vehiculo.getTipo())).thenReturn(10);
		// Act
		hayCapacidad = vehiculoServiceImp.CapacidadEstacionamiento(vehiculo);
		// Assert
		assertEquals(false, hayCapacidad);
	}

	@Test
	public void permitirIngresoParqueadero()  {
		// Arrange
		boolean validarIngreso;
		LocalDate fecha = LocalDate.now().withDayOfMonth(7);
		Mockito.when(vehiculoServiceImpSpy.getfechaActual()).thenReturn(fecha);
		// Act
		validarIngreso = vehiculoServiceImpSpy.IngresarDia();
		// Assert
		assertEquals(true, validarIngreso);
	}

	@Test
	public void NoPermitirIngresoParqueadero() {
		// Arrange
		boolean validarIngreso;
		LocalDate fecha = LocalDate.now().withDayOfMonth(8);
		Mockito.when(vehiculoServiceImpSpy.getfechaActual()).thenReturn(fecha);
		// Act
		validarIngreso = vehiculoServiceImpSpy.IngresarDia();
		// Assert
		assertEquals(false, validarIngreso);
	}

	@Test
	public void VerificarVehiculoParqueado()  {
		// Arrange

		// Act

		// Assert

	}

}
