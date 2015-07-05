package net.erickpineda.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ingrediente", updatable = false, nullable = false)
	private long id;

	@Column(name = "nombre")
	protected String nombre;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredientes", cascade = CascadeType.ALL)
	protected List<Pedido> pedidos;

	@Column(name = "precio")
	protected Double precio;

	public Ingrediente() {
		super();
	}

	public Ingrediente(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Ingrediente [getId()=" + getId() + ", getNombre()="
				+ getNombre() + ", getPrecio()=" + getPrecio() + "]";
	}
}
