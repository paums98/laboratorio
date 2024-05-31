package com.backend.laboratorio.controllers;

import com.backend.laboratorio.dto.Experimento.ExperimentoDTO;
import com.backend.laboratorio.dto.Tecnica.TecnicaDTO;
import com.backend.laboratorio.mappers.Experimento.ExperimentoMapper;
import com.backend.laboratorio.mappers.Tecnica.TecnicaMapper;
import com.backend.laboratorio.model.entity.Experimento;
import com.backend.laboratorio.model.entity.Tecnica;
import com.backend.laboratorio.model.service.Experimento.IExperimentoService;
import com.backend.laboratorio.model.service.Tecnica.ITecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class LaboratorioController {

    @Autowired
    private IExperimentoService experimentoService;

    @Autowired
    private ITecnicaService tecnicaService;

    @Autowired
    private ExperimentoMapper experimentoMapper;

    @Autowired
    private TecnicaMapper tecnicaMapper;

    // MÉTODOS CRUD - EXPERIMENTO

    @GetMapping("/experimentos")
    public ResponseEntity<List<ExperimentoDTO>> index() {

        List<Experimento> experimentos = experimentoService.findAll();

        List<ExperimentoDTO> experimentosDTO = experimentoMapper.toDTO(experimentos);

        return new ResponseEntity<>(experimentosDTO, HttpStatus.OK);

    }

    @GetMapping("/experimentos/{id}")
    public ResponseEntity<ExperimentoDTO> getExperimento(@PathVariable long id){

        Experimento experimento = experimentoService.findById(id);

        ExperimentoDTO experimentoDTO = experimentoMapper.toDTO(experimento);

        return new ResponseEntity<ExperimentoDTO>(experimentoDTO, HttpStatus.OK);

    }

    @PostMapping("/experimentos")
    public ResponseEntity<Experimento> createExperimento(@RequestBody ExperimentoDTO experimentoDTO) {
        Experimento experimento = experimentoMapper.fromDTO(experimentoDTO);
        Experimento newExperimento = experimentoService.addExperimento(experimento);
        return new ResponseEntity<>(newExperimento, HttpStatus.OK);
    }

    @PutMapping("/experimentos/{id}")
    public ResponseEntity<?> updateExperimento(@PathVariable long id, @RequestBody ExperimentoDTO newExperimentoDTO) {
        Experimento experimento = null;
        Map<String, Object> errores = new HashMap<>();

        try {
            Experimento newExperimento = experimentoMapper.fromDTO(newExperimentoDTO);
            experimento = experimentoService.modifyExperimento(id, newExperimento);
        } catch (Exception ex) {
            errores.put("mensaje", "Error al modificar el experimento");
            errores.put("detalle", ex.getMessage());
            return new ResponseEntity<>(errores, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(experimento, HttpStatus.OK);
    }

    @DeleteMapping("/experimentos/{id}")
    public ResponseEntity<Map<String, String>> deleteExperimentoById(@PathVariable long id) throws Exception {
        experimentoService.deleteExperimento(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Experimento borrado correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TÉCNICAS

    @PostMapping("/tecnicas")
    public ResponseEntity<Tecnica> createTecnica(@RequestBody TecnicaDTO tecnicaDTO) {
        Tecnica tecnica = tecnicaMapper.fromDTO(tecnicaDTO);
        Tecnica newTecnica = tecnicaService.addTecnica(tecnica);
        return new ResponseEntity<>(newTecnica, HttpStatus.OK);
    }

    @GetMapping("/tecnicas/{expId}")
    public ResponseEntity<List<TecnicaDTO>> getTecnicasExperimento(@PathVariable long expId){

        List<Tecnica> tecnicas = tecnicaService.findAllByExperimentoId(expId);

        List<TecnicaDTO> tecnicasDTO = tecnicaMapper.toDTO(tecnicas);

        return new ResponseEntity<>(tecnicasDTO, HttpStatus.OK);

    }

}
