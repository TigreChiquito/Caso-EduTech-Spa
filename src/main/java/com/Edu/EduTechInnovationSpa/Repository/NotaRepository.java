package com.Edu.EduTechInnovationSpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Edu.EduTechInnovationSpa.Model.Nota;

@Repository

public interface NotaRepository extends JpaRepository<Nota, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesari

}
