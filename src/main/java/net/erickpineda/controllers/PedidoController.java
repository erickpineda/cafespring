package net.erickpineda.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import net.erickpineda.models.Ingrediente;
import net.erickpineda.models.Pedido;
import net.erickpineda.repositories.IngredienteRepository;
import net.erickpineda.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {
	PedidoRepository pedidoRepo;

	@Autowired
	public PedidoController(PedidoRepository pr) {
		this.pedidoRepo = pr;
	}

	@RequestMapping(value = "/hacerpedido", method = RequestMethod.GET)
	public String creaFormularioPedido(Model model) {
		model.addAttribute("pedidonuevo", new Pedido());
		model.addAttribute("ingredientes", getIngredientes());
		return "hacerpedido";
	}

	@RequestMapping(value = "/hacerpedido", method = RequestMethod.POST)
	public String comprar(
			@RequestParam(value = "nombreingrediente", required = false) String[] ingredientes,
			@Valid Pedido pedido, Model model) {

		String nombre = "Cafe ";

		if (ingredientes == null) {
			pedido.setNombre(nombre + "solo");

			if (pedidoRepo.findByNombre(pedido.getNombre()) != null) {
				pedido.sumarVeces(1);

				pedidoRepo.delete(pedidoRepo.findByNombre(pedido.getNombre()));
			}

			pedidoRepo.save(pedido);
			model.addAttribute("cafe", pedido.getNombre());
			model.addAttribute("total", pedido.getPrecio());

			// response.sendRedirect("/");
			return "pedidook";
		}

		List<Ingrediente> ingre = new ArrayList<Ingrediente>();

		for (int i = 0; i < ingredientes.length; i++) {

			ingre.add(getIngreRepo().findByNombre(ingredientes[i]));
			pedido.total(getIngreRepo().findByNombre(ingredientes[i])
					.getPrecio());

			if (ingredientes.length - 1 == i) {
				nombre += ingredientes[i];
			} else {
				nombre += ingredientes[i] + ", ";
			}
		}

		pedido.setNombre(nombre);
		pedido.setIngredientes(ingre);

		if (pedidoRepo.findByNombre(pedido.getNombre()) != null) {
			pedido.sumarVeces(1);
			pedidoRepo.delete(pedidoRepo.findByNombre(pedido.getNombre()));
		}
		pedidoRepo.save(pedido);

		model.addAttribute("cafe", pedido.getNombre());
		model.addAttribute("total", pedido.getPrecio());
		// response.sendRedirect("/");
		return "pedidook";
	}

	@RequestMapping(value = "/maspopular")
	public String masPopular(Model model) {

		List<Pedido> masPopular = pedidoRepo.findByVecesventas();

		String nombre;

		if (masPopular.size() > 1) {
			nombre = "La ventas más populares son estas";
		} else {
			nombre = "Este café es el más popular";
		}

		model.addAttribute("nombre", nombre);
		model.addAttribute("maspopulares", masPopular);

		return "maspopular";
	}

	private IngredienteRepository getIngreRepo() {
		return IngredienteController.getIngreRepo();
	}

	private List<Ingrediente> getIngredientes() {
		return IngredienteController.getIngredientes();
	}
}
