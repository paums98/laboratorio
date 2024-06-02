package com.backend.laboratorio.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "realiza",
        joinColumns = @JoinColumn(name = "experimento_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "cientifico_id", referencedColumnName = "id"))
    private List<Cientifico> cientificos;

    @OneToMany(mappedBy = "experimento", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<Utiliza> utilizaList;
}
