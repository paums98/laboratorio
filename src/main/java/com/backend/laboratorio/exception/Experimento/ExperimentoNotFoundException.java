package com.backend.laboratorio.exception.Experimento;

public class ExperimentoNotFoundException extends RuntimeException {


    public ExperimentoNotFoundException(Long id) {

        super("Experimento " + id + " no existente");


    }

    public ExperimentoNotFoundException(String message) {

        super(message);
        
    }



}
