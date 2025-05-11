package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_clase;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer alumnosInscr;

    @Column(nullable = false)
    private Integer id_user;

}
