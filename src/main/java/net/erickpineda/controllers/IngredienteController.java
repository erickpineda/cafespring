package net.erickpineda.controllers;

import java.util.List;

import javax.validation.Valid;

import net.erickpineda.models.Ingrediente;
import net.erickpineda.repositories.IngredienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IngredienteController {
	static IngredienteRepository ingreRepo;

	@Autowired
	public IngredienteController(IngredienteRepository ingreRepo) {
		IngredienteController.ingreRepo = ingreRepo;
	}

	@RequestMapping(value = "/crearingrediente", method = RequestMethod.GET)
	public String crearFormularioIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "crearingrediente";
	}

	@RequestMapping(value = "/crearingrediente", method = RequestMethod.POST)
	public String crearIngrediente(@Valid Ingrediente ingre,
			BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			model.addAttribute("mensajeError",
					"No se ha podido crear el ingrediente");
			return "crearingrediente";
		}

		if (ingreRepo.findByNombre(ingre.getNombre()) != null) {
			model.addAttribute("mensajeError", "El ingrediente ya existe");
			return "crearingrediente";
		}

		ingreRepo.save(ingre);

		return "redirect:/";
	}

	public static IngredienteRepository getIngreRepo() {
		return ingreRepo;
	}

	public static List<Ingrediente> getIngredientes() {
		return ingreRepo.findAll();
	}
}
