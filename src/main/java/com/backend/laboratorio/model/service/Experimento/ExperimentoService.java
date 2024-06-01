package com.backend.laboratorio.model.service.Experimento;

import com.backend.laboratorio.model.entity.Experimento;
import com.backend.laboratorio.model.entity.Utiliza;
import com.backend.laboratorio.model.repository.IExperimentoRepository;
import com.backend.laboratorio.model.repository.UsuRol.IUtilizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentoService implements IExperimentoService{

    @Autowired
    private IExperimentoRepository experimentoRepository;

    @Autowired
    private IUtilizaRepository utilizaRepository;

    @Override
    public List<Experimento> findAll() {
        return (List<Experimento>) experimentoRepository.findAll();
    }

    @Override
    public Experimento findById(long id) {
        return experimentoRepository.findById(id).orElse(null);
    }

    @Override
    public Experimento addExperimento(Experimento experimento) {
        return experimentoRepository.save(experimento);
    }

    @Override
    public Experimento modifyExperimento(long id, Experimento experimento) throws Exception {

        Experimento newExperimento = experimentoRepository.findById(id).orElseThrow(() -> new Exception("Error actualizar Experimento: "+id));

        experimento.setId(newExperimento.getId());

        return experimentoRepository.save(experimento);

    }

    @Override
    public void deleteExperimento(long id) throws Exception {

        Experimento experimento = experimentoRepository.findById(id).orElseThrow(() -> new Exception("Error eliminar Experimento: "+id));

        experimentoRepository.delete(experimento);


    }

    @Override
    public List<Integer> findImagenesByExperimentoAndTecnica(long tecnicaId, long experimentoId) {
        return experimentoRepository.findImagenesByExperimentoAndTecnica(tecnicaId, experimentoId);
    }

    @Override
    public Utiliza addUtiliza(Utiliza utiliza) {
        return utilizaRepository.save(utiliza);
    }

    @Override
    public void addCientificoToExperimento(long experimentoId, long cientificoId) {
        experimentoRepository.addCientificoToExperimento(experimentoId, cientificoId);
    }
}
