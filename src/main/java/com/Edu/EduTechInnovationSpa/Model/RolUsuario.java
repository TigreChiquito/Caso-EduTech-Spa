package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RolUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RolUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol;

    @Column(nullable = false)
    private String nombre_rol;

    @Column(nullable = false)
    private String descripcion_rol;


}
