package br.agendamedica.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Paciente extends AbstractEntity {

	private String nome;
	private String rg;
	private String cpf;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataN;
	private String planoS;

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

	public String getPlanoS() {
		return planoS;
	}

	public void setPlanoS(String planoS) {
		this.planoS = planoS;
	}

	public Paciente() {

	}

	public Paciente(String nome, String rg, String cpf, LocalDate dataN, String planoS) {
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.dataN = dataN;
		this.planoS = planoS;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
