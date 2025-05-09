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
@Table(name = "recurso")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Recurso {

    @Id
    private Integer id_recurso;

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="id_curso", nullable = true)
    private Integer id_curso;

    @Column(name="link_recurso", nullable = false )
    private String link_recurso;

    @Column(name= "fecha", nullable = false)
    private Date fecha;

    @Column(name="tipo_recurso", nullable = false)  
    private String tipo_recurso;



}
