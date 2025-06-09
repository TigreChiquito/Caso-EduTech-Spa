package com.Edu.EduTechInnovationSpa.Model;

import java.util.Date;
import jakarta.persistence.*;
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
    private int id_boleta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user", nullable = false)
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura", nullable = false)
    private Asignatura asignatura;


    @Column(name = "fecha", nullable = false)
    private Date fecha;


    @ManyToOne
    @JoinColumn(name = "cupon", referencedColumnName = "id_cupon", nullable = false)
    private Cupon cupon;

        
    @Column(name = "monto_total", nullable = false)
    private float monto_total;

}
