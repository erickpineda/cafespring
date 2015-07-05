package net.erickpineda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.erickpineda.models.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

	List<Pedido> findAll();

	Pedido findByNombre(String nombre);

	Long findById(Pedido pedido);


	@Query("SELECT p FROM Pedido p WHERE p.vecesventas = "
			+ "(SELECT MAX(v.vecesventas) FROM Pedido v )")
	List<Pedido> findByVecesventas();
}
