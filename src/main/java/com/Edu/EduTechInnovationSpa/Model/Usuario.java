package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    
    //@ManyToOne
    //@JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @Column(name = "id_role", nullable = false)
    private int id_role;

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
}
