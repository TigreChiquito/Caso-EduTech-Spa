package com.Edu.EduTechInnovationSpa.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Edu.EduTechInnovationSpa.Model.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
