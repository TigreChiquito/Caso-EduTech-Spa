package com.Edu.EduTechInnovationSpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Edu.EduTechInnovationSpa.Model.Cupon;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Integer> {
}
