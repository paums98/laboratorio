package com.backend.laboratorio.model.repository;

import com.backend.laboratorio.model.entity.Experimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExperimentoRepository extends CrudRepository<Experimento, Long> {


    @Query(value = "INSERT INTO realiza (experimento_id, cientifico_id) VALUES (:experimentoId, :cientificoId)", nativeQuery = true)
    void addCientificoToExperimento(@Param("experimentoId") Long experimentoId, @Param("cientificoId") Long cientificoId);




}
