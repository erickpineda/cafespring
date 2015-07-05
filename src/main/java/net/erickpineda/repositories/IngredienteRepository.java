package net.erickpineda.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.erickpineda.models.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

	List<Ingrediente> findAll();
	Ingrediente findByNombre(String nombre);
	Double findByPrecio(Double precio);
}
