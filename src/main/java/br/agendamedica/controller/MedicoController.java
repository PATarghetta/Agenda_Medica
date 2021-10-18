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

import br.agendamedica.model.Medico;
import br.agendamedica.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController implements ControllerInterface<Medico> {

	@Autowired
	private MedicoService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Medico>> getAll() {

		return ResponseEntity.ok().body(service.findAll());
	}

	@Override
	@GetMapping("{/id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findAll());

	}

	@Override
	@PostMapping
	public ResponseEntity<Medico> post(@RequestBody Medico obj) {
		service.create(obj);
		return ResponseEntity.ok().body(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Medico obj) {
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
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
