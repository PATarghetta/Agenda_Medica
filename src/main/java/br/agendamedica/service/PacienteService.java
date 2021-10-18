package br.agendamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.agendamedica.model.Paciente;
import br.agendamedica.repository.PacienteRepository;

@Service
public class PacienteService implements ServiceInterface<Paciente> {

	@Autowired
	private PacienteRepository repository;

	@Override
	public Paciente create(Paciente obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Paciente findById(Long id) {
		Optional<Paciente> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<Paciente> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Paciente obj) {
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
