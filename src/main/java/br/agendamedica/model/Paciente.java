package br.agendamedica.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Paciente extends AbstractEntity {

	private String nome;
	private String rg;
	private String cpf;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataN;
	@ManyToOne
	private PlanoSaude planoS; 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataN() {
		return dataN;
	}

	public void setDataN(LocalDate dataN) {
		this.dataN = dataN;
	}

	
	public Paciente() {

	}

	
	public PlanoSaude getPlanoS() {
		return planoS;
	}

	public void setPlanoS(PlanoSaude planoS) {
		this.planoS = planoS;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
