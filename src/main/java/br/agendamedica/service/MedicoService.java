package br.agendamedica.service;
		
	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import br.agendamedica.model.Medico;
import br.agendamedica.repository.MedicoRepository;


	@Service
	public class MedicoService implements ServiceInterface<Medico> {

		@Autowired
		private MedicoRepository repository;

		@Override
		public Medico create(Medico obj) {
			repository.save(obj);
			return obj;
		}

		@Override
		public Medico findById(Long id) {
			Optional<Medico> obj = repository.findById(id);
			return obj.orElse(null);
		}

		@Override
		public List<Medico> findAll() {
			return repository.findAll();
		}

		@Override
		public boolean update(Medico obj) {
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

