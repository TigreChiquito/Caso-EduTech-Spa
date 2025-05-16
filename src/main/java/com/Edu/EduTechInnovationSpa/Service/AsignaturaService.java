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
    private AsignaturaRepository claseRepository;

    public List<Asignatura> getAllAsignaturas() {
        return claseRepository.findAll();
    }

    public Asignatura getAsignaturaById(Integer id) {
        return claseRepository.findById(id).orElse(null);
    }

    public Asignatura createAsignatura(Asignatura clase) {
        return claseRepository.save(clase);
    }

    public void deleteAsignatura(Integer id) {
        claseRepository.deleteById(id);
    }
}
