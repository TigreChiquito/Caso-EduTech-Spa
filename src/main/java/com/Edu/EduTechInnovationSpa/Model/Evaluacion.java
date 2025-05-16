package com.Edu.EduTechInnovationSpa.Model;

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

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Date fechaEva;

    @Column(nullable = false)
    private String descripcionEva;

    @Column(nullable = true)
    private int id_nota;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user", nullable = false)
    private Usuario id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_Nota", referencedColumnName = "id_nota",nullable=false)
    private Nota id_Nota;

    @ManyToOne
    @JoinColumn(name = "idSeccion", referencedColumnName = "id_seccion", nullable = false)
    private Seccion idSeccion;


}
