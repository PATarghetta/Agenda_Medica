package br.agendamedica.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.agendamedica.model.Usuario;
import br.agendamedica.repository.UsuarioRepository;

public class BcryptPasswordEncoder {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Autowired
	private UsuarioRepository repo;

	public Usuario create(Usuario obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		repo.save(obj);
		return obj;
	}

}
