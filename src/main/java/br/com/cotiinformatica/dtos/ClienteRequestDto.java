package br.com.cotiinformatica.dtos;

import lombok.Data;

@Data
public class ClienteRequestDto {
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
}
