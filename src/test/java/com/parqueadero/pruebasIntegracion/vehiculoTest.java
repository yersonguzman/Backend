package com.parqueadero.pruebasIntegracion;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.models.Vehiculo;
import com.parqueadero.modelsTest.VehiculoTestDataBuilder;
import com.parqueadero.services.VehiculoServiceImp;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Rollback(value=true)
public class vehiculoTest {
	
	@Autowired
	private VehiculoServiceImp vehiculoServiceImp;
	
	@Test 
	public void IngresarParqueadero() throws ParseException {
		//Arrange
		 Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		//Act
		vehiculoServiceImp.crearVehiculo(vehiculo);
		//Assert
		assertEquals(vehiculo, vehiculo);
	}
	
}
