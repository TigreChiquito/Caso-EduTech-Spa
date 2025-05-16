package com.Edu.EduTechInnovationSpa.Model;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name="nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "asignatura", referencedColumnName = "id_asignatura")
    private Asignatura asignatura;

    @Column(name="vinculo_recurso", nullable = false )
    private String vinculo_recurso;

    @Column(name= "fecha", nullable = false)
    private Date fecha;




}
