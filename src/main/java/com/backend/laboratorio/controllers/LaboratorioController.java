package com.backend.laboratorio.controllers;

import com.backend.laboratorio.dto.Experimento.ExperimentoDTO;
import com.backend.laboratorio.dto.Tecnica.TecnicaDTO;
import com.backend.laboratorio.mappers.Experimento.ExperimentoMapper;
import com.backend.laboratorio.mappers.Tecnica.TecnicaMapper;
import com.backend.laboratorio.model.entity.Experimento;
import com.backend.laboratorio.model.entity.Tecnica;
import com.backend.laboratorio.model.entity.Utiliza;
import com.backend.laboratorio.model.service.Experimento.IExperimentoService;
import com.backend.laboratorio.model.service.Tecnica.ITecnicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Laboratorio", description = "Diferentes consultas necesarias para la gestión de resultados de experimentos")
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

    @Operation(summary = "Obtiene el resultado de los experimentos")
    @GetMapping("/experimentos")
    public ResponseEntity<List<ExperimentoDTO>> index() {
        List<Experimento> experimentos = experimentoService.findAll();
        List<ExperimentoDTO> experimentosDTO = experimentoMapper.toDTO(experimentos);
        return new ResponseEntity<>(experimentosDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un experimento por ID")
    @GetMapping("/experimentos/{id}")
    public ResponseEntity<ExperimentoDTO> getExperimento(
            @Parameter(description = "ID del experimento a obtener") @PathVariable long id) {
        Experimento experimento = experimentoService.findById(id);
        ExperimentoDTO experimentoDTO = experimentoMapper.toDTO(experimento);
        return new ResponseEntity<>(experimentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Crea un nuevo experimento")
    @PostMapping("/experimentos")
    public ResponseEntity<Experimento> createExperimento(
            @Parameter(description = "Datos del experimento a crear") @RequestBody ExperimentoDTO experimentoDTO) {
        Experimento experimento = experimentoMapper.fromDTO(experimentoDTO);
        Experimento newExperimento = experimentoService.addExperimento(experimento);
        return new ResponseEntity<>(newExperimento, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un experimento existente")
    @PutMapping("/experimentos/{id}")
    public ResponseEntity<?> updateExperimento(
            @Parameter(description = "ID del experimento a actualizar") @PathVariable long id,
            @Parameter(description = "Datos nuevos del experimento") @RequestBody ExperimentoDTO newExperimentoDTO) {
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

    @Operation(summary = "Elimina un experimento por ID")
    @DeleteMapping("/experimentos/{id}")
    public ResponseEntity<Map<String, String>> deleteExperimentoById(
            @Parameter(description = "ID del experimento a eliminar") @PathVariable long id) throws Exception {
        experimentoService.deleteExperimento(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Experimento borrado correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TÉCNICAS

    @Operation(summary = "Obtiene todas las técnicas")
    @GetMapping("/tecnicas")
    public ResponseEntity<List<TecnicaDTO>> findAll(){

        List<Tecnica> tecnicas = tecnicaService.findAll();
        List<TecnicaDTO> tecnicasDTO = tecnicaMapper.toDTO(tecnicas);
        return new ResponseEntity<>(tecnicasDTO, HttpStatus.OK);

    }

    @Operation(summary = "Obtiene una técnica por ID")
    @GetMapping("/tecnica/{id}")
    public ResponseEntity<TecnicaDTO> getTecnica(
            @Parameter(description = "ID del experimento a obtener") @PathVariable long id) {
        Tecnica tecnica = tecnicaService.findById(id);
        TecnicaDTO tecnicaDTO = tecnicaMapper.toDTO(tecnica);
        return new ResponseEntity<>(tecnicaDTO, HttpStatus.OK);
    }

    @Operation(summary = "Crea una nueva técnica")
    @PostMapping("/tecnicas")
    public ResponseEntity<Tecnica> createTecnica(
            @Parameter(description = "Datos de la técnica a crear") @RequestBody TecnicaDTO tecnicaDTO) {
        Tecnica tecnica = tecnicaMapper.fromDTO(tecnicaDTO);
        Tecnica newTecnica = tecnicaService.addTecnica(tecnica);
        return new ResponseEntity<>(newTecnica, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza una técnica existente")
    @PutMapping("/técnicas/{id}")
    public ResponseEntity<?> updateTecnica(
            @Parameter(description = "ID de la técnica a actualizar") @PathVariable long id,
            @Parameter(description = "Datos nuevos de la técnica") @RequestBody TecnicaDTO tecnicaDTO) {
        Tecnica tecnica = null;
        Map<String, Object> errores = new HashMap<>();

        try {
            Tecnica newTecnica = tecnicaMapper.fromDTO(tecnicaDTO);
            tecnica = tecnicaService.modifyTecnica(id, newTecnica);
        } catch (Exception ex) {
            errores.put("mensaje", "Error al modificar la técnica");
            errores.put("detalle", ex.getMessage());
            return new ResponseEntity<>(errores, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(tecnica, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una técnica por ID")
    @DeleteMapping("/tecnicas/{id}")
    public ResponseEntity<Map<String, String>> deleteTecnica(
            @Parameter(description = "ID de la técnica a eliminar") @PathVariable long id) throws Exception {
        tecnicaService.deleteTecnica(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Técnica borrada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene las técnicas asociadas a un experimento")
    @GetMapping("/tecnicas/{expId}")
    public ResponseEntity<List<TecnicaDTO>> getTecnicasExperimento(
            @Parameter(description = "ID del experimento cuyas técnicas se quieren obtener") @PathVariable long expId) {
        List<Tecnica> tecnicas = tecnicaService.findAllByExperimentoId(expId);
        List<TecnicaDTO> tecnicasDTO = tecnicaMapper.toDTO(tecnicas);
        return new ResponseEntity<>(tecnicasDTO, HttpStatus.OK);
    }

    // AÑADIR RELACIÓN DE TÉCNICA Y USUARIO

    @Operation(summary = "Crea una nueva relación entre experimento y técnica")
    @PostMapping("/utiliza")
    public ResponseEntity<Utiliza> createUtiliza(@RequestBody Utiliza utiliza){

        Utiliza newUtiliza = experimentoService.addUtiliza(utiliza);

        return new ResponseEntity<>(newUtiliza, HttpStatus.OK);

    }

    // OBTENER IMÁGENES
    @Operation(summary = "Obtiene las imágenes de un experimento y una técnica")
    @GetMapping("/imagenes")
    public ResponseEntity<List<Integer>> getImagenesExperimentoTecnica(long idTecnica, long idExperimento){

        List<Integer> listaImagenes = experimentoService.findImagenesByExperimentoAndTecnica(idTecnica, idExperimento);
        return  new ResponseEntity<>(listaImagenes, HttpStatus.OK);

    }

}
