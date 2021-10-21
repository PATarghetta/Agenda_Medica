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

import br.agendamedica.model.PlanoSaude;
import br.agendamedica.service.PlanoSaudeService;

@RestController
@RequestMapping("/planosaudes")
public class PlanoSaudeController implements ControllerInterface<PlanoSaude> {

	@Autowired
	private PlanoSaudeService service;

	@Override
	@GetMapping
	public ResponseEntity<List<PlanoSaude>> getAll() {

		return ResponseEntity.ok().body(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {

		return ResponseEntity.ok(service.findById(id));
	}

	@Override
	@PostMapping
	public ResponseEntity<PlanoSaude> post(@RequestBody PlanoSaude obj) {
		service.create(obj);
		return ResponseEntity.ok().body(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody PlanoSaude obj) {
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
