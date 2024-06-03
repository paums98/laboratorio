package com.backend.laboratorio.exception.Utiliza;

public class RelacionExistenteException extends RuntimeException {
    public RelacionExistenteException(Long experimentoId, Long tecnicaId) {
        super("La relación entre el experimento " + experimentoId + " y la técnica " + tecnicaId + " ya existe");
    }

    public RelacionExistenteException(String message) {
        super(message);
    }
}
