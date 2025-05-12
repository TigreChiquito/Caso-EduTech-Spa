package com.Edu.EduTechInnovationSpa.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edu.EduTechInnovationSpa.Model.Recurso;
import com.Edu.EduTechInnovationSpa.Repository.RecursoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> getAllRecursos() {
        return recursoRepository.findAll();
    }

    public Recurso getRecursoById(Integer id_recurso) {
        return recursoRepository.findById(id_recurso).orElse(null);
    }

    public Recurso createRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public void deleteRecurso(Integer id_recurso) {
        recursoRepository.deleteById(id_recurso);
    }
}
