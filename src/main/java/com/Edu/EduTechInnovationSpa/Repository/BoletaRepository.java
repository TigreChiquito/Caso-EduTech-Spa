package com.Edu.EduTechInnovationSpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Edu.EduTechInnovationSpa.Model.Boleta;

@Repository



public interface BoletaRepository extends JpaRepository<Boleta, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
