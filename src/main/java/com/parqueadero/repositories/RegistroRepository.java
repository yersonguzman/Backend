package com.parqueadero.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parqueadero.models.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

	@Query(value = "select * from registro  where fk_placa = :placa order by fecha_ingreso DESC", nativeQuery = true)
	Optional<List<Registro>> findtop1ByPlacaOrderByFechaIngresoDesc(@Param("placa") String placa);

}

