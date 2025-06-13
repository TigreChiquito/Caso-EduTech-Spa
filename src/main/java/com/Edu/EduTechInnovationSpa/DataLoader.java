package com.Edu.EduTechInnovationSpa;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Model.Cupon;
import com.Edu.EduTechInnovationSpa.Model.Evaluacion;
import com.Edu.EduTechInnovationSpa.Model.Nota;
import com.Edu.EduTechInnovationSpa.Model.Recurso;
import com.Edu.EduTechInnovationSpa.Model.RolUsuario;
import com.Edu.EduTechInnovationSpa.Model.Seccion;
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
    Faker faker = new Faker(new Locale("en"));
    Random random = new Random();

    String[] userTypes = {"Profesor", "Alumno", "Administrador"};
    rolUsuarioRepository.deleteAll();
    userRepository.deleteAll();
    asignaturaRepository.deleteAll();
    seccionRepository.deleteAll();
    cuponRepository.deleteAll();
    recursoRepository.deleteAll();

    //rol usuarios
    for (int i = 0; i < userTypes.length; i++) {
        RolUsuario rolUsuario = new RolUsuario();
        rolUsuario.setNombre_rol(userTypes[i]);
        rolUsuario.setDescripcion_rol("Rol de " + userTypes[i]);
        rolUsuarioRepository.save(rolUsuario);
        
    }
    
    
    

    List<RolUsuario> roles = rolUsuarioRepository.findAll();


    // usuarios
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


    //asignaturas
    for (int i = 0; i < 5; i++) {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(faker.educator().course());
        asignatura.setDescripcion("Clase de " + asignatura.getNombre());
        float price = 30000 + random.nextFloat() * (100000 - 30000);  // precios
        price = Math.round(price * 100f) / 100f;  // redondear
        asignatura.setCosto(price);
        asignaturaRepository.save(asignatura);
    }
    


    // Secciones
    // Validar usuarios con rol profesor
    List<Usuario> profesores = userRepository.findAll().stream()
        .filter(u -> u.getRol() != null && u.getRol().getNombre_rol().equals("Profesor"))
        .toList();

    List<Asignatura> asignaturas = asignaturaRepository.findAll();

    for (int i = 0; i < 5; i++) {
        Seccion seccion = new Seccion();
        seccion.setCupos(faker.number().numberBetween(10, 40));

        Usuario profesor = profesores.get(random.nextInt(profesores.size()));
        seccion.setDocente(profesor.getFirst_name() + " " + profesor.getLast_name());
        Date fechaInicio = faker.date().future(10, java.util.concurrent.TimeUnit.DAYS);
        Date fechaTermino = faker.date().future(90, java.util.concurrent.TimeUnit.DAYS, fechaInicio);
        seccion.setFecha_inicio(fechaInicio);
        seccion.setFecha_termino(fechaTermino);
        Asignatura asig = asignaturas.get(random.nextInt(asignaturas.size()));
        seccion.setAsignatura(asig);

        seccionRepository.save(seccion);
    }

    // cupones
    for (int i = 0; i < 5; i++) {
        Cupon cupon = new Cupon();
        cupon.setCode(faker.commerce().promotionCode());  
        cupon.setDiscount((float) Math.round((random.nextFloat() * 50 + 10) * 100f) / 100f); 
        Date start = faker.date().future(10, TimeUnit.DAYS);
        Date end = faker.date().future(60, TimeUnit.DAYS, start);  

        cupon.setStart_date(start);
        cupon.setEnd_date(end);
        
        cupon.setUse_limit(random.nextInt(50) + 1);  
        cupon.setUsed(random.nextInt(10));           
        
        cuponRepository.save(cupon);
    }

    // recursos
    for (int i = 0; i < 5; i++) {
        Recurso recurso = new Recurso();
        recurso.setId_recurso(i + 1);
        recurso.setNombre(faker.book().title());
        recurso.setVinculo_recurso(faker.internet().url());
        java.util.Date fechaRecurso = faker.date().past(30, TimeUnit.DAYS);
        recurso.setFecha(new java.sql.Timestamp(fechaRecurso.getTime()));

        Asignatura asignatura = asignaturas.get(random.nextInt(asignaturas.size()));
        recurso.setId_asignatura(asignatura.getId_asignatura());

        recursoRepository.save(recurso);
    }


    


    List<Usuario> usuarios = userRepository.findAll();
    List<Seccion> secciones = seccionRepository.findAll();


    //evaluacion
    for (int i = 0; i < 5; i++) {
        Evaluacion evaluacion = new Evaluacion();

        evaluacion.setTitulo(faker.book().title());
        java.util.Date fechaEvaluacion = faker.date().future(15, TimeUnit.DAYS);
        evaluacion.setFechaEva(new java.sql.Date(fechaEvaluacion.getTime()));
        evaluacion.setDescripcionEva(faker.lorem().sentence(10));
        evaluacion.setPuntajeMax(faker.number().numberBetween(00,  100));
        evaluacion.setPuntajeObt(faker.number().numberBetween(00,  evaluacion.getPuntajeMax()));

        Usuario usuario = usuarios.get(random.nextInt(usuarios.size()));
        evaluacion.setId_usuario(usuario);


        Seccion seccion = secciones.get(random.nextInt(secciones.size()));
        evaluacion.setIdSeccion(seccion);

        evaluacionRepository.save(evaluacion);

    
}
}


