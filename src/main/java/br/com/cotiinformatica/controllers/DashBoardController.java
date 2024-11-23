package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.TipoQuantidadeDto;
import br.com.cotiinformatica.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {
	@GetMapping
	public List<TipoQuantidadeDto> getAll() throws Exception{
		var clienteRepository = new ClienteRepository();
		return clienteRepository.getGroupByTipo();
	}
}
