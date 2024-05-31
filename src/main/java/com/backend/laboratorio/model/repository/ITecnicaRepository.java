package com.backend.laboratorio.model.repository;

import com.backend.laboratorio.model.entity.Tecnica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ITecnicaRepository extends CrudRepository<Tecnica, Long> {

    @Query("SELECT t FROM Tecnica t JOIN Utiliza et ON t.id = et.tecnica.id WHERE et.experimento.id = :experimentoId")
    List<Tecnica> findAllByExperimentoId(Long experimentoId);

}
