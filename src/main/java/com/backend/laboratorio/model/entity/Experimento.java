package com.backend.laboratorio.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experimento")
public class Experimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private LocalDateTime fecha;

    @Column
    private boolean repeticion;

    @Column
    private String experimentoPasado;

    @ManyToMany
    @JoinTable(name = "realiza",
        joinColumns = @JoinColumn(name = "experimento_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "cientifico_id", referencedColumnName = "id"))
    private List<Cientifico> cientificos;

    @OneToMany(mappedBy = "experimento")
    public List<Utiliza> utilizaList;
}
