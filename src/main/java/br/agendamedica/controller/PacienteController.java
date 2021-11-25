package br.agendamedica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agendamedica.model.Paciente;
import br.agendamedica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements ControllerInterface<Paciente> {

	@Autowired
	private PacienteService service;

	@Override
	@Operation(summary = "Este end point devolve uma lista dos pacientes cadastrados")
	@GetMapping(produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devolve os pacientes cadastrados"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso" ),
	})
	public ResponseEntity<List<Paciente>> getAll() {

		return ResponseEntity.ok().body(service.findAll());
	}

	@Operation(summary = "Este end point devolve um paciente dado seu id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devolve um paciente dado seu id"),
			@ApiResponse(responseCode = "404", description = "Não há nenhum paciente com o id fornecido"),
			@ApiResponse(responseCode = "401", description = "Você não está autorizado a acessar esse recurso"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso" ),
	})
	@Override
	@GetMapping(value =  "/{id}", produces = "application/json")
	public ResponseEntity<?> get(@PathVariable Long id) {

		return ResponseEntity.ok(service.findById(id));
	}

	@Operation(summary = "Este end point cadastra um paciente")
	@Override
	@PostMapping(produces = "application/json")
	public ResponseEntity<Paciente> post(@RequestBody Paciente obj) {
		service.create(obj);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Este end point é usado para alterar um paciente")
	@Override
	@PutMapping(produces = "application/json")
	public ResponseEntity<?> put(@RequestBody Paciente obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@Operation(summary = "Este end point é usado para excluir um paciente")
	@Override
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().body("Deletado com Sucesso!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
