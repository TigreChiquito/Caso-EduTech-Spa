package com.Edu.EduTechInnovationSpa.Service;

import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    public Usuario getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // saber el rol que tiene un usuario
    public String getUserRole(Integer id) {
        Usuario user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return user.getRol() != null ? user.getRol().toString() : null;
        }
        return null;
    }

    public Usuario createUser(Usuario user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public boolean userExist(Integer id){
        Usuario usuario = getUserById(id);
        if (usuario == null) {
            return false;
        }
        return true;
    }

}
