package br.agendamedica.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.agendamedica.model.Usuario;
import br.agendamedica.repository.UsuarioRepository;
import br.agendamedica.security.UserDetailsImpl;

@Service
public class UsuarioService implements ServiceInterface<Usuario> {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;

	public static UserDetailsImpl authenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return (UserDetailsImpl) auth.getPrincipal();
		}
		return null;
	}

	@Override
	public Usuario create(Usuario obj) {
		obj.setSenha(passwordEnconder.encode(obj.getSenha()));
		repository.save(obj);
		return obj;
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Usuario obj) {
		if (repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}
