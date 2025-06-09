package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Seccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_seccion;

    @Column(name = "cupos", nullable = false)
    private int cupos;

    @Column(name = "docente", nullable = false)
    private String docente;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fecha_inicio;


    @Column(name = "fecha_termino", nullable = false)
    private Date fecha_termino;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura", nullable = false)
    private Asignatura asignatura;
    
}
