package com.Edu.EduTechInnovationSpa;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.Edu.EduTechInnovationSpa.Model.RolUsuario;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Repository.AsignaturaRepository;
import com.Edu.EduTechInnovationSpa.Repository.BoletaRepository;
import com.Edu.EduTechInnovationSpa.Repository.CuponRepository;
import com.Edu.EduTechInnovationSpa.Repository.EvaluacionRepository;
import com.Edu.EduTechInnovationSpa.Repository.NotaRepository;
import com.Edu.EduTechInnovationSpa.Repository.RecursoRepository;
import com.Edu.EduTechInnovationSpa.Repository.RolUsuarioRepository;
import com.Edu.EduTechInnovationSpa.Repository.SeccionRepository;
import com.Edu.EduTechInnovationSpa.Repository.UserRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component

public class DataLoader implements CommandLineRunner {


    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CuponRepository cuponRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Autowired
    private SeccionRepository seccionRepository;
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private BoletaRepository boletaRepository;
    @Autowired
    private RecursoRepository recursoRepository;

    @Override
    public void run(String... args) throws Exception {
    Faker faker = new Faker();
    Random random = new Random();
    String[] userTypes = {"Profesor", "Alumno", "Administrador"};
    rolUsuarioRepository.deleteAll();
    userRepository.deleteAll();



    for (int i = 0; i < userTypes.length; i++) {
        RolUsuario rolUsuario = new RolUsuario();
        rolUsuario.setNombre_rol(userTypes[i]);
        rolUsuario.setDescripcion_rol("Rol de " + userTypes[i]);
        rolUsuarioRepository.save(rolUsuario);
        
    }
    

    List<RolUsuario> roles = rolUsuarioRepository.findAll();



    for (int i = 0; i < 30; i++) {
        Usuario usuario = new Usuario();
        usuario.setFirst_name(faker.name().firstName());
        usuario.setLast_name(faker.name().lastName());
        usuario.setRun(faker.number().digits(9));
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setBirthdate(faker.date().birthday());
        usuario.setRol(roles.get(random.nextInt(roles.size())));
        userRepository.save(usuario);
    }





    }

}


