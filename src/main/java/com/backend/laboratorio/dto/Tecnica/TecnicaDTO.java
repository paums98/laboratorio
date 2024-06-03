package com.backend.laboratorio.dto.Tecnica;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicaDTO {

    @NotNull
    private long id;

    @NotEmpty
    @Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

}
