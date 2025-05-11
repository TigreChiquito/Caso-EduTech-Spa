package com.Edu.EduTechInnovationSpa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edu.EduTechInnovationSpa.Model.Evaluacion;
import com.Edu.EduTechInnovationSpa.Repository.EvaluacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> getAllEvaluacion() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion creatEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion getEvaluacionById(Integer id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    public void deleteEvaluacion(Integer id) {
        evaluacionRepository.deleteById(id);
    }
}
