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
    private int id_nota;

    @Column(name = "nota", nullable = false)
    private int nota;

    //@ManyToOne
    //@JoinColumn(name = "id_usuario", referencedColumnName = "id_user", nullable = false)
    @Column (name = "descripcion", nullable = false)
    private String descripcion;

    

}
