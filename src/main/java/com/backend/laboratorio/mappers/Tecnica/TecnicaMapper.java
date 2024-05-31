package com.backend.laboratorio.mappers.Tecnica;

import com.backend.laboratorio.dto.Experimento.ExperimentoDTO;
import com.backend.laboratorio.dto.Tecnica.TecnicaDTO;
import com.backend.laboratorio.model.entity.Experimento;
import com.backend.laboratorio.model.entity.Tecnica;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@AllArgsConstructor
public class TecnicaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TecnicaDTO toDTO(Tecnica tecnica){
        return modelMapper.map(tecnica, TecnicaDTO.class);
    }

    public Tecnica fromDTO(TecnicaDTO tecnicaDTO){
        return modelMapper.map(tecnicaDTO, Tecnica.class);
    }

    public List<TecnicaDTO> toDTO(List<Tecnica> tecnicas){
        return tecnicas.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
