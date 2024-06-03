package com.backend.laboratorio.exception.Utiliza;

public class UtilizaNotFoundException extends RuntimeException {
    public UtilizaNotFoundException(Long experimentoId, Long tecnicaId) {
        super("Relación entre el experimento " + experimentoId + " y la técnica " + tecnicaId + " no encontrada");
    }

    public UtilizaNotFoundException(String message) {
        super(message);
    }
}
