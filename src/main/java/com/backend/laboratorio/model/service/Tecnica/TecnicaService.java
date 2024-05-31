package com.backend.laboratorio.model.service.Tecnica;

import com.backend.laboratorio.model.entity.Tecnica;
import com.backend.laboratorio.model.repository.ITecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicaService implements ITecnicaService{

    @Autowired
    private ITecnicaRepository tecnicaRepository;

    @Override
    public List<Tecnica> findAllByExperimentoId(Long experimentoId) {
        return tecnicaRepository.findAllByExperimentoId(experimentoId);
    }

    @Override
    public Tecnica addTecnica(Tecnica tecnica) {
        return tecnicaRepository.save(tecnica);
    }
}
