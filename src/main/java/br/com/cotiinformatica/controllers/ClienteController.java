package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ClienteRequestDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.repositories.ClienteRepository;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@PostMapping
	public String post(@RequestBody ClienteRequestDto request) throws Exception { // inserir
		var cliente =  new Cliente();
		cliente.setId(UUID.randomUUID());
		cliente.setNome(request.getNome());
		cliente.setCpf(request.getCpf());
		cliente.setTelefone(request.getTelefone());
		cliente.setEmail(request.getEmail());
		
		var clienteRepository = new ClienteRepository();
		
		if(!clienteRepository.isExists(cliente.getCpf(), cliente.getId())) {
			
			clienteRepository.create(cliente);
			return "Cliente cadastrado com sucesso!";
		}
		else {
			return "O CPF " + cliente.getCpf() + " informado já está cadastrado para outro cliente. Tente novamente.";
		}
			
	}

	@PutMapping("{id}") //recebe o id que vai ser passado pelo url do serviço
	public String put(@PathVariable UUID id, @RequestBody ClienteRequestDto request) throws Exception{ // editar
	
		var clineteRepository = new ClienteRepository();
		var cliente = clineteRepository.getById(id);
		
		if(cliente!= null) {
			cliente.setNome(request.getNome());
			cliente.setCpf(request.getCpf());
			cliente.setTelefone(request.getTelefone());
			cliente.setEmail(request.getEmail());
			
			if(!clineteRepository.isExists(cliente.getCpf(), cliente.getId())) {
				clineteRepository.update(cliente);
				return "cliente atualizado com sucesso!";
			}
			else {
				return "Nao é possivel atualizar os dados pois o CPF " + cliente.getCpf() + "já pertecence a outro cliente!" ;
			}
			
			
		}else {
			return "cliente não encontrado. Verifique o ID informado.";
		}
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) throws Exception{ // excluir
		var clienteRepository = new ClienteRepository();
		var cliente = clienteRepository.getById(id);
		
		if(cliente!= null) {
			clienteRepository.delete(id);
			
			return "cliente ecluido com sucesso!";
		}else {
			return "cliente não encontrado. Verifique o ID informado.";
		}
	}

	@GetMapping
	public List<Cliente> get() throws Exception { // leitura
		var clienteRepository = new ClienteRepository();

		return clienteRepository.getAll();
	}
	
	@GetMapping("{id}")
	public Cliente getById(@PathVariable UUID id) throws Exception {
		var clienteRepository = new ClienteRepository();
		return clienteRepository.getById(id);
	}

}
