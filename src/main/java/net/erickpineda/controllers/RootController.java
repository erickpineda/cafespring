package net.erickpineda.controllers;

import net.erickpineda.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	@Autowired
	private PedidoRepository pedidoRepo;

	@RequestMapping(value = "/")
	public String principal() {
		return "index";
	}
}
