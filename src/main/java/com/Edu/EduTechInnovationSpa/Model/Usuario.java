package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "run", unique = true, nullable = false, length = 13)
    private String run;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "Birthdate", nullable = true)
    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "rol", referencedColumnName = "id_rol", nullable = true)
    private RolUsuario rol;
}
