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

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Asignatura getAsignaturaById(Integer id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public void deleteAsignatura(Integer id) {
        asignaturaRepository.deleteById(id);
    }
}
