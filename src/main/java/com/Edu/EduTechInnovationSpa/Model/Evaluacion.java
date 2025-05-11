package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_evaluacion;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Data fechaEva;

    @Column(nullable = false)
    private String descripcionEva;

    @Column(nullable = false)
    private Integer puntajeObte;

    @Column(nullable = true)
    private Integer id_nota;

}
