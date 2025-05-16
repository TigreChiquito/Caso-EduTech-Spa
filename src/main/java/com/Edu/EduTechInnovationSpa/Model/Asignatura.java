package com.Edu.EduTechInnovationSpa.Model;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Asignatura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_asignatura;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int alumnosInscr;

    @Column(nullable = false)
    private int id_user;

}
