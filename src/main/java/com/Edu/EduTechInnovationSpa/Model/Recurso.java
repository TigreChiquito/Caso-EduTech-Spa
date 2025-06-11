package com.Edu.EduTechInnovationSpa.Model;

import java.util.Date;
import jakarta.persistence.*;
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

    //@ManyToOne
    //@JoinColumn(name = "id_usuario", referencedColumnName = "id_user", nullable = false)
    @Column(name = "id_asignatura", nullable = false)
    private int id_asignatura;

    @Column(name="vinculo_recurso", nullable = false )
    private String vinculo_recurso;

    @Column(name= "fecha", nullable = false)
    private Date fecha;




}
