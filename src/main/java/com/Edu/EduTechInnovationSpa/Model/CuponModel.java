package com.Edu.EduTechInnovationSpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "cupon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuponModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cupon;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "discount", nullable = false)
    private float discount;

    @Column(name = "start_date", nullable = false)
    private Date start_date;

    @Column(name = "end_date", nullable = false)
    private Date end_date;

    @Column(name = "use_limit", nullable = false)
    private Integer use_limit;

    @Column(name = "used", nullable = false)
    private Integer used;
}
