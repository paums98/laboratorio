package com.backend.laboratorio.model.service.Experimento;

import com.backend.laboratorio.model.entity.Experimento;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExperimentoService {

    List<Experimento> findAll();

    Experimento findById(long id);

    Experimento addExperimento(Experimento experimento);

    Experimento modifyExperimento(long id, Experimento experimento) throws Exception;

    void deleteExperimento(long id) throws Exception;

    List<Integer> findImagenesByExperimentoAndTecnica (long tecnicaId, long experimentoId);

}
