package br.agendamedica.model;

import javax.persistence.Entity;

@Entity
public class Medico extends AbstractEntity {

	private String nome;
	private String crm;
	private String espec;
	private String contato;

	public Medico(String nome, String crm, String espec, String contato) {
		this.nome = nome;
		this.crm = crm;
		this.espec = espec;
		this.contato = contato;
		
		
	}
	
	public Medico() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspec() {
		return espec;
	}

	public void setEspec(String espec) {
		this.espec = espec;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
