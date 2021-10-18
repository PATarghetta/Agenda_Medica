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

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements ControllerInterface<Paciente> {

	@Autowired
	private PacienteService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Paciente>> getAll() {

		return ResponseEntity.ok().body(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {

		return ResponseEntity.ok(service.findById(id));
	}

	@Override
	@PostMapping
	public ResponseEntity<Paciente> post(@RequestBody Paciente obj) {
		service.create(obj);
		return ResponseEntity.ok().body(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Paciente obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

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
