package com.parqueadero.pruebasIntegracion;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.models.Vehiculo;
import com.parqueadero.modelsTest.VehiculoTestDataBuilder;
import com.parqueadero.services.VehiculoServiceImp;
import com.parqueadero.tarifas.PagoSalidaVehiculo;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Rollback(value = true)
@AutoConfigureTestDatabase //(connection = EmbeddedDatabaseConnection.H2)
public class vehiculoTest {

	@Autowired
	private VehiculoServiceImp vehiculoServiceImp;

	@Test
	public void IngresarParqueadero() throws ParseException {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		// Act
		vehiculoServiceImp.crearVehiculo(vehiculo);
		// Assert
		assertNotNull(vehiculo.getFechaIngreso());
	}

	@Test
	public void SalirParqueadero() throws ParseException {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		PagoSalidaVehiculo pagoVehiculo;
		// Act
		pagoVehiculo = vehiculoServiceImp.salirDelParqueadero(vehiculo.getPlaca());
		// Assert
		assertNotNull(pagoVehiculo.getTotalPagar());
	}

}
