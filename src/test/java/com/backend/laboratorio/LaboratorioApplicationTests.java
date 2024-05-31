package com.backend.laboratorio;

import com.backend.laboratorio.controllers.LaboratorioController;
import com.backend.laboratorio.dto.Experimento.ExperimentoDTO;
import com.backend.laboratorio.dto.Tecnica.TecnicaDTO;
import com.backend.laboratorio.mappers.Experimento.ExperimentoMapper;
import com.backend.laboratorio.mappers.Tecnica.TecnicaMapper;
import com.backend.laboratorio.model.entity.Experimento;
import com.backend.laboratorio.model.entity.Tecnica;
import com.backend.laboratorio.model.service.Experimento.IExperimentoService;
import com.backend.laboratorio.model.service.Tecnica.ITecnicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LaboratorioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private IExperimentoService experimentoService;

	@Mock
	private ITecnicaService tecnicaService;

	@Mock
	private ExperimentoMapper experimentoMapper;

	@Mock
	private TecnicaMapper tecnicaMapper;

	@InjectMocks
	private LaboratorioController laboratorioController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(laboratorioController).build();
	}

	@Test
	void testGetAllExperimentos() throws Exception {
		Experimento experimento = new Experimento();
		experimento.setId(1L);
		List<Experimento> experimentos = Arrays.asList(experimento);
		ExperimentoDTO experimentoDTO = new ExperimentoDTO();
		experimentoDTO.setId(1L);
		List<ExperimentoDTO> experimentosDTO = Arrays.asList(experimentoDTO);

		when(experimentoService.findAll()).thenReturn(experimentos);
		when(experimentoMapper.toDTO(experimentos)).thenReturn(experimentosDTO);

		mockMvc.perform(get("/api/experimentos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L));
	}

	@Test
	void testGetExperimentoById() throws Exception {
		Experimento experimento = new Experimento();
		experimento.setId(1L);
		ExperimentoDTO experimentoDTO = new ExperimentoDTO();
		experimentoDTO.setId(1L);

		when(experimentoService.findById(1L)).thenReturn(experimento);
		when(experimentoMapper.toDTO(experimento)).thenReturn(experimentoDTO);

		mockMvc.perform(get("/api/experimentos/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L));
	}

	@Test
	void testCreateExperimento() throws Exception {
		ExperimentoDTO experimentoDTO = new ExperimentoDTO();
		Experimento experimento = new Experimento();
		Experimento newExperimento = new Experimento();
		newExperimento.setId(1L);

		when(experimentoMapper.fromDTO(any(ExperimentoDTO.class))).thenReturn(experimento);
		when(experimentoService.addExperimento(any(Experimento.class))).thenReturn(newExperimento);

		mockMvc.perform(post("/api/experimentos")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L));
	}

	@Test
	void testUpdateExperimento() throws Exception {
		ExperimentoDTO experimentoDTO = new ExperimentoDTO();
		Experimento experimento = new Experimento();
		experimento.setId(1L);

		when(experimentoMapper.fromDTO(any(ExperimentoDTO.class))).thenReturn(experimento);
		when(experimentoService.modifyExperimento(any(Long.class), any(Experimento.class))).thenReturn(experimento);

		mockMvc.perform(put("/api/experimentos/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L));
	}

	@Test
	void testDeleteExperimento() throws Exception {
		mockMvc.perform(delete("/api/experimentos/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.mensaje").value("Experimento borrado correctamente"));
	}

	@Test
	void testCreateTecnica() throws Exception {
		TecnicaDTO tecnicaDTO = new TecnicaDTO();
		Tecnica tecnica = new Tecnica();
		Tecnica newTecnica = new Tecnica();
		newTecnica.setId(1L);

		when(tecnicaMapper.fromDTO(any(TecnicaDTO.class))).thenReturn(tecnica);
		when(tecnicaService.addTecnica(any(Tecnica.class))).thenReturn(newTecnica);

		mockMvc.perform(post("/api/tecnicas")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L));
	}

	@Test
	void testGetTecnicasByExperimentoId() throws Exception {
		Tecnica tecnica = new Tecnica();
		tecnica.setId(1L);
		List<Tecnica> tecnicas = Arrays.asList(tecnica);
		TecnicaDTO tecnicaDTO = new TecnicaDTO();
		tecnicaDTO.setId(1L);
		List<TecnicaDTO> tecnicasDTO = Arrays.asList(tecnicaDTO);

		when(tecnicaService.findAllByExperimentoId(1L)).thenReturn(tecnicas);
		when(tecnicaMapper.toDTO(tecnicas)).thenReturn(tecnicasDTO);

		mockMvc.perform(get("/api/tecnicas/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L));
	}
}
