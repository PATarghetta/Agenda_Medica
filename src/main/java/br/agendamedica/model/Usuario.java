package br.agendamedica.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario extends AbstractEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nome;
    private String login;
    private String senha;



    public Usuario() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getSenha() {
        return senha;
    }

    @JsonProperty
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPerfis(Set<Integer> perfis) {
        this.perfis = perfis;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_perfil")
    private Set<Integer> perfis = new HashSet<>();

    public Set<TipoPerfil> getPerfis() {
        return perfis.stream().map(x -> TipoPerfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(TipoPerfil perfil) {
        this.perfis.add(perfil.getCod());
    }
}