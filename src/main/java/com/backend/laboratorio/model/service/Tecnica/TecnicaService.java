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
    public List<Tecnica> findAll() {
        return (List<Tecnica>) tecnicaRepository.findAll();
    }

    @Override
    public Tecnica findById(long id) {
        return tecnicaRepository.findById(id).orElse(null);
    }

    @Override
    public Tecnica addTecnica(Tecnica tecnica) {
        return tecnicaRepository.save(tecnica);
    }

    @Override
    public Tecnica modifyTecnica(long id, Tecnica tecnica) throws Exception {
        Tecnica newTecnica = tecnicaRepository.findById(id).orElseThrow(() -> new Exception("Error al actualizar Tecnica"));

        tecnica.setId(newTecnica.getId());

        return tecnicaRepository.save(tecnica);
    }

    @Override
    public void deleteTecnica(long id) throws Exception {

        Tecnica tecnica = tecnicaRepository.findById(id).orElseThrow(() -> new Exception("Error eliminar Técnica: "+id));

        tecnicaRepository.delete(tecnica);

    }
}
