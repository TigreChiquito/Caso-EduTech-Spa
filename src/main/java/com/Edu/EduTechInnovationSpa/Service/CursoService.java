package com.Edu.EduTechInnovationSpa.Service;

import com.Edu.EduTechInnovationSpa.Model.Curso;
import com.Edu.EduTechInnovationSpa.Repository.CursoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Integer id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteCurso(Integer id) {
        cursoRepository.deleteById(id);
    }
    
}
