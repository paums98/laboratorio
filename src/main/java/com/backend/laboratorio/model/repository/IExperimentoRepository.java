package com.backend.laboratorio.model.repository;

import com.backend.laboratorio.model.entity.Experimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExperimentoRepository extends CrudRepository<Experimento, Long> {

    @Query(value = "SELECT ui.imagen \n" +
            "FROM experimento_tecnica ui \n" +
            "JOIN utiliza_imagenes u ON ui.id = u.utiliza_id\n" +
            "JOIN experimento e ON ui.experimento_id = e.id\n" +
            "JOIN tecnica t ON ui.tecnica_id = t.id\n" +
            "WHERE t.id = :tecnicaId AND e.id = :experimentoId", nativeQuery = true)
    List<Integer> findImagenesByExperimentoAndTecnica(@Param("tecnicaId") Long tecnicaId,
                                                      @Param("experimentoId") Long experimentoId);


    @Query(value = "INSERT INTO realiza (experimento_id, cientifico_id) VALUES (:experimentoId, :cientificoId)", nativeQuery = true)
    void addCientificoToExperimento(@Param("experimentoId") Long experimentoId, @Param("cientificoId") Long cientificoId);




}
