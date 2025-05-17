package com.Edu.EduTechInnovationSpa.Service;

import com.Edu.EduTechInnovationSpa.Model.Seccion;
import com.Edu.EduTechInnovationSpa.Repository.SeccionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Transactional
public class SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;

    public List<Seccion> getAllSeccions() {
        return seccionRepository.findAll();
    }

    public Seccion getSeccionById(Integer id) {
        return seccionRepository.findById(id).orElse(null);
    }

    public Seccion createSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    public void deleteSeccion(Integer id) {
        seccionRepository.deleteById(id);
    }

}
