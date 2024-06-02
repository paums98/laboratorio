package com.backend.laboratorio.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experimento_tecnica")
public class Utiliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    @CollectionTable(name = "utiliza_imagenes", joinColumns = @JoinColumn(name = "utiliza_id"))
    @Column(name = "imagen")
    private List<String> imagenes;

    @ManyToOne
    @JoinColumn(name = "experimento_id")
    private Experimento experimento;

    @ManyToOne
    @JoinColumn(name = "tecnica_id")
    private Tecnica tecnica;

}
