package com.backend.laboratorio.model.service.Experimento;

import com.backend.laboratorio.model.entity.Experimento;
import com.backend.laboratorio.model.entity.Utiliza;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExperimentoService {

    List<Experimento> findAll();

    Experimento findById(long id);

    Experimento addExperimento(Experimento experimento);

    Experimento modifyExperimento(long id, Experimento experimento) throws Exception;

    void deleteExperimento(long id) throws Exception;

    Utiliza addUtiliza(Utiliza utiliza);

    void addCientificoToExperimento(long experimentoId, long cientificoId);

    public Experimento agregarTecnica(Long experimentoId, Long tecnicaId);

    public List<String> getImagenesByExperimentoAndTecnica(Long experimentoId, Long tecnicaId);

    public Experimento agregarImagen(Long experimentoId, Long tecnicaId, String imagen);

    Experimento eliminarImagen(Long experimentoId, Long tecnicaId, String imagen) throws Exception;

    Utiliza findUtilizaByExperimentoIdAndTecnicaId(Long experimentoId, Long tecnicaId);
    void deleteUtiliza(Long utilizaId);
}
