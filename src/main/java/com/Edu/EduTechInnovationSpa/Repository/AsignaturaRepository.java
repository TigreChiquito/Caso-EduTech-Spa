package com.Edu.EduTechInnovationSpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Edu.EduTechInnovationSpa.Model.Asignatura;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

}
