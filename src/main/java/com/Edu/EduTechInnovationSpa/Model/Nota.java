package com.Edu.EduTechInnovationSpa.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Integer id_nota;

    @Column(name = "nota", nullable = false)
    private Integer nota;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

}
