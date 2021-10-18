package br.agendamedica.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.agendamedica.model.Usuario;
import br.agendamedica.model.dto.CredenciaisDTO;
import br.agendamedica.repository.UsuarioRepository;


public class JWTAuthenticationFilter extends 
UsernamePasswordAuthenticationFilter{
	 private AuthenticationManager authenticationManager;
	 private JWTUtil jwtUtil;
	 public UsuarioRepository usuarioRepo;
	 public JWTAuthenticationFilter(AuthenticationManager 
	 authenticationManager, JWTUtil jwtUtil, UsuarioRepository usuarioRepo) {
	 this.authenticationManager = authenticationManager;
	 this.jwtUtil = jwtUtil;
	 this.usuarioRepo = usuarioRepo;
	 }
	 
	 @Override
	 public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest 
	request, HttpServletResponse response) throws AuthenticationException {
	 try {
	 CredenciaisDTO creds = new ObjectMapper()
	 .readValue(request.getInputStream(), CredenciaisDTO.class);
	 UsernamePasswordAuthenticationToken authToken = new 
	 UsernamePasswordAuthenticationToken(creds.getLogin(), 
	 creds.getSenha(), new ArrayList<>());
	 Authentication auth = 
	 (Authentication) authenticationManager.authenticate(authToken);
	 return (org.springframework.security.core.Authentication) auth;
	 } catch (IOException e) { throw new RuntimeException(e); }
	 }

	 
	 protected void successfulAuthentication(HttpServletRequest request, 
	 HttpServletResponse response, FilterChain chain,
	 Authentication authResult) throws IOException, ServletException {
	 String username = ((UserDetailsImpl) ((org.springframework.security.core.Authentication) authResult).getPrincipal())
	 .getUsername();
	 String token = jwtUtil.generateToken(username);
	 response.addHeader("Authentication", "Bearer " + token);
	 response.addHeader("access-control-expose-headers","Authorization");
	 Usuario User = usuarioRepo.findByLogin(username);
	 response.setContentType("application/json");
	 response.getWriter().append(jsonAuth(token, User));
	 }
	 private String jsonAuth(String token, Usuario usuario) {
		 return "{\"token\": \"" + token + "\", " + ", " + 
		          "\"name\": \"" + usuario.getLogin() + "\" " +
		          "\"profile\": " + usuario.getPerfis().stream()
		             .map(x -> "\"" + x + "\"")
		               .collect(Collectors.toList()) +  "}";
	 }

	 protected void unsuccessfulAuthentication(HttpServletRequest request,
	 HttpServletResponse response, AuthenticationException failed)
	 throws java.io.IOException, javax.servlet.ServletException {
	 response.setStatus(401);
	 response.setContentType("application/json");
	 response.getWriter().append(jsonError());
	 }
	 private String jsonError() {
	     return "{"
	 		+ "\"timestamp\": "	+ new Date().getTime() + ", " 
	        + "\"status\": 401,"
	        + " " + "\"error\": \"Não autorizado\", "
	        + "\"message\": \"Email ou senha inválidos\", " 
	        + "\"path\": \"/login\"}";
	 }}


