package com.Edu.EduTechInnovationSpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Edu.EduTechInnovationSpa.Model.Seccion;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {
}
