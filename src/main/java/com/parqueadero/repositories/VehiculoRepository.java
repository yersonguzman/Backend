package com.parqueadero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parqueadero.models.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {

	@Query(value = "select count(v) from Vehiculo v where v.tipo = :tipo")
	Integer contarporClaseVehiculo(@Param("tipo") String tipo);

	

	

}
