package com.backend.laboratorio.model.repository;

import com.backend.laboratorio.model.entity.Experimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExperimentoRepository extends CrudRepository<Experimento, Long> {

    @Query(value = "SELECT ui.imagen FROM Utiliza ui " +
            "JOIN ui.experimento e " +
            "JOIN ui.tecnica t " +
            "WHERE t.id = :tecnicaId AND e.id = :experimentoId", nativeQuery = true)
    List<Integer> findImagenesByExperimentoAndTecnica(@Param("tecnicaId") Long tecnicaId,
                                                      @Param("experimentoId") Long experimentoId);

}
