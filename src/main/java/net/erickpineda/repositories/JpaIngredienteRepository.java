package net.erickpineda.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.erickpineda.models.Ingrediente;

import org.springframework.stereotype.Repository;

@Repository
public abstract class JpaIngredienteRepository implements IngredienteRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Ingrediente> findAll() {
		return this.entityManager.createQuery("SELECT i FROM Ingrediente i",
				Ingrediente.class).getResultList();
	}

	@Override
	public Ingrediente findByNombre(String nombre) {
		return this.entityManager
				.createQuery("SELECT i.nombre FROM Ingrediente i",
						Ingrediente.class).getSingleResult();
	}

	@Override
	public Double findByPrecio(Double precio) {
		return this.entityManager
				.createQuery("SELECT i.precio FROM Ingrediente i",
						Ingrediente.class).getSingleResult().getPrecio();
	}
}
