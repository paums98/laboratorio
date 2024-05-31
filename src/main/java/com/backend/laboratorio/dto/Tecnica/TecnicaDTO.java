package com.backend.laboratorio.dto.Tecnica;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicaDTO {

    private long id;

    private String nombre;

    private String descripcion;

}
