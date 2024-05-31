package com.backend.laboratorio.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "experimento_tecnica")
public class Utiliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    @CollectionTable(name = "utiliza_imagenes", joinColumns = @JoinColumn(name = "utiliza_id"))
    @Column(name = "imagen")
    private List<Integer> imagenes;

    @ManyToOne
    @JoinColumn(name = "experimento_id")
    private Experimento experimento;

    @ManyToOne
    @JoinColumn(name = "tecnica_id")
    private Tecnica tecnica;

}
