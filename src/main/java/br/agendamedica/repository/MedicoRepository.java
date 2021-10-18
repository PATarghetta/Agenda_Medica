package br.agendamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agendamedica.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
