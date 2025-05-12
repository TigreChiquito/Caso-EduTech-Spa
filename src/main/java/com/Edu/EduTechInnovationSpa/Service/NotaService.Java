package com.Edu.EduTechInnovationSpa.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edu.EduTechInnovationSpa.Model.Nota;
import com.Edu.EduTechInnovationSpa.Repository.NotaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    public Nota getNotaById(Integer id_nota) {
        return notaRepository.findById(id_nota).orElse(null);
    }

    public Nota createNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public void deleteNota(Integer id_nota) {
        notaRepository.deleteById(id_nota);
    }
}
