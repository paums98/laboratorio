package com.backend.laboratorio.model.service.Tecnica;

import com.backend.laboratorio.model.entity.Tecnica;

import java.util.List;

public interface ITecnicaService {

    List<Tecnica> findAllByExperimentoId(Long experimentoId);

    Tecnica addTecnica(Tecnica tecnica);

}
