package net.erickpineda.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import net.erickpineda.models.Pedido;

@Repository
public abstract class JpaPedidoRepository implements PedidoRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long findById(Pedido pedido) {
		return this.em
				.createQuery(
						"SELECT p FROM Pedido p " + "WHERE p.id_pedido = '"
								+ pedido.getId() + "'", Pedido.class)
				.getSingleResult().getId();
	}

	@Override
	public Pedido findByNombre(String nombre) {
		return this.em
				.createQuery(
						"SELECT p FROM Pedido p " + "WHERE p.nombre = '"
								+ nombre + "'", Pedido.class).getSingleResult();
	}

	@Override
	public List<Pedido> findAll() {
		return this.em.createQuery("SELECT p FROM Pedido p", Pedido.class)
				.getResultList();
	}

}
