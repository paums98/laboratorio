package com.backend.laboratorio.model.service.Experimento;

import com.backend.laboratorio.exception.Experimento.ExperimentoNotFoundException;
import com.backend.laboratorio.exception.Tecnica.TecnicaNotFoundException;
import com.backend.laboratorio.exception.Utiliza.RelacionExistenteException;
import com.backend.laboratorio.exception.Utiliza.UtilizaNotFoundException;
import com.backend.laboratorio.model.entity.Experimento;
import com.backend.laboratorio.model.entity.Tecnica;
import com.backend.laboratorio.model.entity.Utiliza;
import com.backend.laboratorio.model.repository.IExperimentoRepository;
import com.backend.laboratorio.model.repository.ITecnicaRepository;
import com.backend.laboratorio.model.repository.UsuRol.IUtilizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperimentoService implements IExperimentoService {

    @Autowired
    private IExperimentoRepository experimentoRepository;

    @Autowired
    private ITecnicaRepository tecnicaRepository;

    @Autowired
    private IUtilizaRepository utilizaRepository;

    @Override
    public List<Experimento> findAll() {
        return (List<Experimento>) experimentoRepository.findAll();
    }

    @Override
    public Experimento findById(long id) {
        return experimentoRepository.findById(id).orElseThrow(() -> new  ExperimentoNotFoundException(id));
    }

    @Override
    public Experimento addExperimento(Experimento experimento) {
        return experimentoRepository.save(experimento);
    }

    @Override
    public Experimento modifyExperimento(long id, Experimento experimento) throws Exception {

        Experimento newExperimento = experimentoRepository.findById(id).orElseThrow(() -> new Exception("Error actualizar Experimento: " + id));

        experimento.setId(newExperimento.getId());

        return experimentoRepository.save(experimento);

    }

    @Override
    public void deleteExperimento(long id) throws Exception {

        Experimento experimento = experimentoRepository.findById(id).orElseThrow(() -> new Exception("Error eliminar Experimento: " + id));

        experimentoRepository.delete(experimento);


    }


    @Override
    public Utiliza addUtiliza(Utiliza utiliza) {
        return utilizaRepository.save(utiliza);
    }

    @Override
    public void addCientificoToExperimento(long experimentoId, long cientificoId) {
        experimentoRepository.addCientificoToExperimento(experimentoId, cientificoId);
    }

    @Transactional
    public Experimento agregarTecnica(Long experimentoId, Long tecnicaId) {
        Experimento experimento = experimentoRepository.findById(experimentoId)
                .orElseThrow(() -> new ExperimentoNotFoundException("Experimento no encontrado"));
        Tecnica tecnica = tecnicaRepository.findById(tecnicaId)
                .orElseThrow(() -> new TecnicaNotFoundException("Técnica no encontrada"));

        boolean existeRelacion = experimento.getUtilizaList().stream()
                .anyMatch(utiliza -> utiliza.getTecnica().equals(tecnica));

        if (existeRelacion) {
            throw new RelacionExistenteException(experimentoId, tecnicaId);
        }

        Utiliza utiliza = new Utiliza();
        utiliza.setExperimento(experimento);
        utiliza.setTecnica(tecnica);
        utilizaRepository.save(utiliza);

        return experimento;
    }


    public List<String> getImagenesByExperimentoAndTecnica(Long experimentoId, Long tecnicaId) {

        Utiliza utiliza = utilizaRepository.findByExperimentoIdAndTecnicaId(experimentoId, tecnicaId);
        return utiliza != null ? utiliza.getImagenes() : new ArrayList<>();

    }


    @Transactional
    @Override
    public Experimento agregarImagen(Long experimentoId, Long tecnicaId, String imagen) {
        Experimento experimento = experimentoRepository.findById(experimentoId)
                .orElseThrow(() -> new ExperimentoNotFoundException("Experimento no encontrado"));
        Tecnica tecnica = tecnicaRepository.findById(tecnicaId)
                .orElseThrow(() -> new TecnicaNotFoundException("Técnica no encontrada"));

        Utiliza utiliza = utilizaRepository.findByExperimentoIdAndTecnicaId(experimentoId, tecnicaId);
        if (utiliza == null) {
            utiliza = new Utiliza();
            utiliza.setExperimento(experimento);
            utiliza.setTecnica(tecnica);
        }
        utiliza.getImagenes().add(imagen);
        utilizaRepository.save(utiliza);

        return experimento;
    }

    @Transactional
    @Override
    public Experimento eliminarImagen(Long experimentoId, Long tecnicaId, String imagen) {
        Experimento experimento = experimentoRepository.findById(experimentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Experimento no encontrado"));
        Tecnica tecnica = tecnicaRepository.findById(tecnicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tecnica no encontrada"));

        Utiliza utiliza = utilizaRepository.findByExperimentoIdAndTecnicaId(experimentoId, tecnicaId);
        if (utiliza != null) {
            utiliza.getImagenes().remove(imagen);
            utilizaRepository.save(utiliza);
        } else {
            throw new UtilizaNotFoundException(experimentoId, tecnicaId);
        }

        return experimento;
    }

    @Override
    public Utiliza findUtilizaByExperimentoIdAndTecnicaId(Long experimentoId, Long tecnicaId) {
        return utilizaRepository.findByExperimentoIdAndTecnicaId(experimentoId, tecnicaId);
    }

    @Override
    public void deleteUtiliza(Long utilizaId) {
        utilizaRepository.deleteById(utilizaId);
    }


}