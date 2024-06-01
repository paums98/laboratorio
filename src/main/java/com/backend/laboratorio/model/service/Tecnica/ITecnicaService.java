package com.backend.laboratorio.model.service.Tecnica;

import com.backend.laboratorio.model.entity.Tecnica;

import java.util.List;

public interface ITecnicaService {

    List<Tecnica> findAllByExperimentoId(Long experimentoId);


    List<Tecnica> findAll();

    Tecnica findById(long id);

    Tecnica addTecnica(Tecnica tecnica);

    Tecnica modifyTecnica(long id, Tecnica tecnica) throws Exception;

    void deleteTecnica(long id) throws Exception;

}
