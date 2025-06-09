package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.*;
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

    @Column (name = "descripcion", nullable = false)
    private String descripcion;

    

}
