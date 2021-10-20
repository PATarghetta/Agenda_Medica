package br.agendamedica.model;

import javax.persistence.Entity;

@Entity
public class PlanoSaude extends AbstractEntity {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

	public PlanoSaude() {
		
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
