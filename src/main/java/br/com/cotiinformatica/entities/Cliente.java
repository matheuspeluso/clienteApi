package br.com.cotiinformatica.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
	
	private UUID id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	
}
