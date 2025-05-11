package com.Edu.EduTechInnovationSpa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edu.EduTechInnovationSpa.Model.Clase;
import com.Edu.EduTechInnovationSpa.Repository.ClaseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClaseService {
    @Autowired
    private ClaseRepository claseRepository;

    public List<Clase> getAllUsers() {
        return claseRepository.findAll();
    }

    public Clase getClaseById(Integer id) {
        return claseRepository.findById(id).orElse(null);
    }

    public Clase createClase(Clase clase) {
        return claseRepository.save(clase);
    }

    public void deleteClase(Integer id) {
        claseRepository.deleteById(id);
    }
}
