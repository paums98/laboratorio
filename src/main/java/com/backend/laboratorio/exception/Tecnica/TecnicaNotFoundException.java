package com.backend.laboratorio.exception.Tecnica;

public class TecnicaNotFoundException extends RuntimeException {
    public TecnicaNotFoundException(Long id) {
        super("Técnica " + id + " no encontrada");
    }

    public TecnicaNotFoundException(String message) {
        super(message);
    }
}
