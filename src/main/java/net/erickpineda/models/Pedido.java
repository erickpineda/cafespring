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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pedido", updatable = false, nullable = false)
	private long id;

	@Column(name = "nombre")
	protected String nombre;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinTable(name = "pedido_ingrediente", joinColumns = 
	{ @JoinColumn(name = "id_pedido") }, inverseJoinColumns = 
	{ @JoinColumn(name = "id_ingrediente") })
	protected List<Ingrediente> ingredientes;

	@Column(name = "vecesventas")
	protected int vecesventas = 1;

	@Column(name = "precio")
	protected double precio = 2.5;

	public Pedido() {
		super();
	}

	public Pedido(List<Ingrediente> ingre, int vecesVentas) {
		this.ingredientes = ingre;
		this.vecesventas = vecesVentas;
	}
	
	public Pedido(String nombre, int vecesVentas, double precio){
		this.nombre = nombre;
		this.vecesventas = vecesVentas;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public int getVecesVentas() {
		return vecesventas;
	}

	public void setVecesVentas(int vecesVentas) {
		this.vecesventas = vecesVentas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void total(double pagar) {
		this.precio += pagar;
	}

	public int sumarVeces(int repite) {
		return this.vecesventas += repite;
	}

	@Override
	public String toString() {
		return "Pedido [getId()=" + getId() + ", getIngredientes()="
				+ getIngredientes() + ", getVecesVentas()=" + getVecesVentas()
				+ "]";
	}

}
