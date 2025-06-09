package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user", nullable = false)
    private Usuario id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_Nota", referencedColumnName = "id_nota", nullable = false)
    private Nota id_Nota;

    @ManyToOne
    @JoinColumn(name = "idSeccion", referencedColumnName = "id_seccion", nullable = false)
    private Seccion idSeccion;

}
