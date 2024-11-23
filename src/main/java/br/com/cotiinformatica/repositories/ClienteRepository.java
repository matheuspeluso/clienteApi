package br.com.cotiinformatica.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.dtos.TipoQuantidadeDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.enums.TipoCliente;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ClienteRepository {
	public void create(Cliente cliente) throws Exception {
		var connection = ConnectionFactory.getConnection();
		var statement = connection.prepareStatement("INSERT INTO cliente(id,nome,cpf,telefone,email, tipo) VALUES(?,?,?,?,?,?)");
		
		statement.setString(1, cliente.getId().toString());
		statement.setString(2, cliente.getNome());
		statement.setString(3, cliente.getCpf());
		statement.setString(4, cliente.getTelefone());
		statement.setString(5, cliente.getEmail());
		statement.setString(6, cliente.getTipo().toString());
		statement.execute();
		connection.close();
	}
	
	public void update(Cliente cliente) throws Exception {
		var connection = ConnectionFactory.getConnection();
		var statement = connection.prepareStatement("UPDATE cliente SET nome=? , cpf=?, telefone=?, email=?, tipo=? WHERE id=?");
		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getCpf());
		statement.setString(3, cliente.getTelefone());
		statement.setString(4, cliente.getEmail());
		statement.setString(5, cliente.getTipo().toString());
		statement.setString(6, cliente.getId().toString());
		statement.execute();
		connection.close();
		
	}
	
	public void delete(UUID id) throws Exception {
		var connection = ConnectionFactory.getConnection();
		var statement = connection.prepareStatement("DELETE FROM cliente WHERE id=?");
		statement.setString(1, id.toString());
		statement.execute();
		connection.close();
	}
	
	public List<Cliente> getAll() throws Exception {
		var connection = ConnectionFactory.getConnection();
		var statement = connection.prepareStatement("SELECT id, nome, cpf, telefone, email, tipo FROM cliente ORDER BY nome");
		
		var resultSet = statement.executeQuery(); // executando a consulta e retornanod os dados da consulta
		var lista = new ArrayList<Cliente>();
		
		while(resultSet.next()) {
			var cliente = new Cliente();
			
			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setCpf(resultSet.getString("cpf"));
			cliente.setTelefone(resultSet.getString("telefone"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTipo(TipoCliente.valueOf(resultSet.getString("tipo")));
			
			lista.add(cliente);
		}
		connection.close();

		return lista;
	}
	
	public Cliente getById(UUID id) throws Exception{
		var connection = ConnectionFactory.getConnection();
		var statement = connection.prepareStatement("SELECT id, nome, cpf, telefone, email, tipo FROM cliente WHERE id=?");
		
		statement.setString(1, id.toString());
		var resultSet = statement.executeQuery(); // executando a consulta e retornanod os dados da consulta
		
		Cliente cliente = null;
		
		if(resultSet.next()) {
			cliente = new Cliente();
			
			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setCpf(resultSet.getString("cpf"));
			cliente.setTelefone(resultSet.getString("telefone"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTipo(TipoCliente.valueOf(resultSet.getString("tipo")));
		}
		connection.close();

		return cliente;
	}
	
	
	public boolean isExists(String cpf, UUID id) throws Exception {
		var connection = ConnectionFactory.getConnection();
		var statement = connection.prepareStatement("SELECT COUNT(cpf) AS qtd FROM cliente WHERE cpf =? AND id  <> ?");
		statement.setString(1, cpf);
		statement.setString(2 , id.toString());
		
		var resultSet = statement.executeQuery();
		var result = false;
		
		if(resultSet.next()) {
			var qtd = resultSet.getInt("qtd");
			result = (qtd == 1);
		}
		
		connection.close();
		
		return result;
	}
	
	public List<TipoQuantidadeDto> getGroupByTipo() throws Exception{
		var connection = ConnectionFactory.getConnection();
		
		var statement = connection.prepareStatement("SELECT tipo, count(*) AS quantidade FROM cliente GROUP BY tipo");
		var resultSet = statement.executeQuery();
		
		var lista = new ArrayList<TipoQuantidadeDto>();
		
		while(resultSet.next()) {
			var dto = new TipoQuantidadeDto();
			dto.setTipo(resultSet.getString("tipo"));
			dto.setQuantidade(resultSet.getInt("quantidade"));
			
			lista.add(dto);
		}
		
		connection.close();
		
		return lista;
	}
	
}
