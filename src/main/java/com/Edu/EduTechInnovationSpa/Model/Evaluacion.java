package com.Edu.EduTechInnovationSpa.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private int id_evaluacion;

    @Column(name = "Titulo", nullable = false)
    private String titulo;

    @Column(name = "FechaEva", nullable = false)
    private Date fechaEva;

    @Column(name = "Descripcion", nullable = false)
    private String descripcionEva;

    @Column(name = "PuntajeMax", nullable = false)
    private int PuntajeMax;
    
    @Column(name = "PuntajeObtenido", nullable = false)
    private int PuntajeObt;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user", nullable = false)
    private Usuario id_usuario;

    @ManyToOne
    @JoinColumn(name = "idSeccion", referencedColumnName = "id_seccion", nullable = false)
    private Seccion idSeccion;

}
