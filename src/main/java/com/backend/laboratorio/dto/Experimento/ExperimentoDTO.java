package com.backend.laboratorio.dto.Experimento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperimentoDTO implements Serializable {

    private long id;

    private String nombre;

    private String descripcion;

    private LocalDateTime fecha;

    private String experimentoPasado;


}
