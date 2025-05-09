package com.Edu.EduTechInnovationSpa.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nota")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Nota {

    @Id
    private Integer id_nota;

    @Column(name = "nota", nullable = false)
    private Integer nota;


    @Column(name = "id_usuario", nullable = false)
    private String id_usuario;


    @Column(name= "id_evaluacion", nullable= false)
    private Integer id_evaluacion;


    @Column(name = "fecha", nullable = false)
    private Date fecha;


}
