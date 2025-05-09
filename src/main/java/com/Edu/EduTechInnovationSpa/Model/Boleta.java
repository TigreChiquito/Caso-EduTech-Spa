package com.Edu.EduTechInnovationSpa.Model;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "boleta")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_boleta;

    @Column(name = "id_usuario", nullable = false)
    private String id_usuario;


    @Column(name = "id_curso", nullable = false)
    private String id_curso;


    @Column(name = "fecha", nullable = false)
    private Date fecha;


    @Column(name= "code", nullable = true)
    private String code;

        
    @Column(name = "monto_total", nullable = false)
    private float monto_total;

}
