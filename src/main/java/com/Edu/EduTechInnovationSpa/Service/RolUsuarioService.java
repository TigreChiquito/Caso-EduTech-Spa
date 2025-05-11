package com.Edu.EduTechInnovationSpa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edu.EduTechInnovationSpa.Model.RolUsuario;
import com.Edu.EduTechInnovationSpa.Repository.RolUsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolUsuarioService {
    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    public List<RolUsuario> getAllUsers() {
        return rolUsuarioRepository.findAll();
    }

    public RolUsuario getRolById(Integer id) {
        return rolUsuarioRepository.findById(id).orElse(null);
    }

    public RolUsuario createRolUsuario(RolUsuario rolUsuario) {
        return rolUsuarioRepository.save(rolUsuario);
    }

    public void deleteRolUsuario(Integer id) {
        rolUsuarioRepository.deleteById(id);
    }

}
