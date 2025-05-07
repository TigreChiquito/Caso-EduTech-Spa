package com.Edu.EduTechInnovationSpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Edu.EduTechInnovationSpa.Model.CursoModel;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Integer> {
}
