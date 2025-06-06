package com.Edu.EduTechInnovationSpa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Repository.AsignaturaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AsignaturaService {
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // Cualquiera puede ver las asignaturas
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    // Cualquiera puede ver las asignaturas
    public Asignatura getAsignaturaById(Integer id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    // Un administrador puede crear una asignatura (cambiar)
    public Asignatura createAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    // Un administrador puede borrar una asignatura (cambiar)
    public void deleteAsignatura(Integer id) {
        asignaturaRepository.deleteById(id);
    }
}
