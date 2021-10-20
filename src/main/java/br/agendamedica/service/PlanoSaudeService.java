package br.agendamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.agendamedica.model.PlanoSaude;
import br.agendamedica.repository.PlanoSaudeRepository;

@Service
public class PlanoSaudeService implements ServiceInterface<PlanoSaude> {

	@Autowired
	private PlanoSaudeRepository repository;

	@Override
	public PlanoSaude create(PlanoSaude obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public PlanoSaude findById(Long id) {
		Optional<PlanoSaude> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<PlanoSaude> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(PlanoSaude obj) {
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
