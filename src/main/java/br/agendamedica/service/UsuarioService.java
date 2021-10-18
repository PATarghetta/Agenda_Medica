package br.agendamedica.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.agendamedica.security.UserDetailsImpl;

@Service
public interface UsuarioService {
	public static UserDetailsImpl authenticated() {
		 Authentication auth = SecurityContextHolder
		 .getContext().getAuthentication();
		 if (auth != null) {
		 return (UserDetailsImpl) auth.getPrincipal();
		 }
		 return null;
		 }


}
