package com.Edu.EduTechInnovationSpa.Repository;

import com.Edu.EduTechInnovationSpa.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
}
