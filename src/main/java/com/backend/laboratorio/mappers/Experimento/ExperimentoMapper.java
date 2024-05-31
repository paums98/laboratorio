package com.backend.laboratorio.mappers.Experimento;

import com.backend.laboratorio.dto.Experimento.ExperimentoDTO;
import com.backend.laboratorio.model.entity.Experimento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ExperimentoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ExperimentoDTO toDTO(Experimento experimento){
        return modelMapper.map(experimento, ExperimentoDTO.class);
    }

    public Experimento fromDTO(ExperimentoDTO experimentoDTO){
        return modelMapper.map(experimentoDTO, Experimento.class);
    }

    public List<ExperimentoDTO> toDTO(List<Experimento> experimentos){
        return experimentos.stream().map(this::toDTO).collect(Collectors.toList());
    }



}
