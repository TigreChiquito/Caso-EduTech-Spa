package com.Edu.EduTechInnovationSpa.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edu.EduTechInnovationSpa.Model.Boleta;
import com.Edu.EduTechInnovationSpa.Repository.BoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {
    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> getAllBoletas() {
        return boletaRepository.findAll();
    }

    public Boleta getBoletaById(Integer id_boleta) {
        return boletaRepository.findById(id_boleta).orElse(null);
    }

    public Boleta createBoleta(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public void deleteBoleta(Integer id_boleta) {
        boletaRepository.deleteById(id_boleta);
    }
}
