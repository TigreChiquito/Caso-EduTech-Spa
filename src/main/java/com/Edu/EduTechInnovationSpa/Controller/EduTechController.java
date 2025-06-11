package com.Edu.EduTechInnovationSpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.Edu.EduTechInnovationSpa.Model.*;
import com.Edu.EduTechInnovationSpa.Service.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1/edu")
@Tag(name = "EduTech API", description = "API for managing EduTech Innovation SPA") 
public class EduTechController {

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private UserService usuarioService;

    @Autowired
    private CuponService cuponService;

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private BoletaService boletaService;

    // TEST

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }

    // - - - - - - - - - - Controladores de Usuarios - - - - - - - - - -

    @GetMapping("/Usuarios")
    @Operation(summary = "List all users", description = "Returns a list of all registered users in the system.")
    public ResponseEntity<List<Usuario>> ListarUsuarios() {

        List<Usuario> usuarios = usuarioService.getAllUsers();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/Usuarios/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a user by their unique ID.")
    public ResponseEntity<Usuario> ObtenerUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUserById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/Usuarios")
    @Operation(summary = "Create a new user", description = "Creates a new user in the system with the provided details.")
    public ResponseEntity<Usuario> CrearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.createUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/Usuarios/{id}")
    @Operation(summary = "Update user by ID", description = "Updates the details of an existing user by their unique ID.")
    public ResponseEntity<Usuario> ActualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {

            Usuario user = usuarioService.getUserById(id);

            user.setFirst_name(usuario.getFirst_name());
            user.setLast_name(usuario.getLast_name());
            user.setRol(usuario.getRol());
            user.setRun(usuario.getRun());
            user.setEmail(usuario.getEmail());
            user.setBirthdate(usuario.getBirthdate());
            Usuario usuarioActualizado = usuarioService.createUser(user);
            return ResponseEntity.ok(usuarioActualizado);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/Usuarios/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user from the system by their unique ID.")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // - - - - - - - - - - Controladores de Seccion - - - - - - - - - -

    @GetMapping("/Secciones")
    @Operation(summary = "List all sections", description = "Returns a list of all sections available in the system.")
    public ResponseEntity<List<Seccion>> ListarCursos() {
        List<Seccion> secciones = seccionService.getAllSeccions();
        if (secciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(secciones);
    }

    @GetMapping("/Secciones/{id}")
    @Operation(summary = "Get section by ID", description = "Returns a section by its unique ID.")
    public ResponseEntity<Seccion> getSecciones(@PathVariable int id) {
        Seccion seccion = seccionService.getSeccionById(id);
        if (seccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(seccion);

    }

    @PostMapping("/Seccion")
    @Operation(summary = "Create a new section", description = "Creates a new section with the provided details.")
    public ResponseEntity<Seccion> CrearSeccion(@RequestBody Seccion seccion) {
        Seccion newSeccion = seccionService.createSeccion(seccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSeccion);
    }

    @PostMapping("/Seccion/{id}")
    @Operation(summary = "Update section by ID", description = "Updates the details of an existing section by its unique ID.")
    public ResponseEntity<Seccion> updateSeccion(@PathVariable int id, @RequestBody Seccion seccion) {
        try {

            Seccion secc = seccionService.getSeccionById(id);

            secc.setCupos(seccion.getCupos());
            secc.setDocente(seccion.getDocente());
            secc.setFecha_inicio(seccion.getFecha_inicio());
            secc.setFecha_termino(seccion.getFecha_termino());
            Seccion seccionUpd = seccionService.createSeccion(secc);
            return ResponseEntity.ok(seccionUpd);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/Seccion/{id}")
    @Operation(summary = "Delete section by ID", description = "Deletes a section from the system by its unique ID.")
    public ResponseEntity<?> eliminarSeccion(@PathVariable int id) {
        try {
            seccionService.deleteSeccion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // - - - - - - - - - - Controladores de Cupon - - - - - - - - - -

    @GetMapping("/Cupon")
    @Operation(summary = "List all coupons", description = "Returns a list of all available coupons in the system.")
    public ResponseEntity<List<Cupon>> ListarCupones() {
        List<Cupon> cupones = cuponService.getAllCupons();
        if (cupones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cupones);
    }

    // --------Controlador RolUsuario

    @GetMapping("/RolUsuario")
    @Operation(summary = "List all user roles", description = "Returns a list of all user roles defined in the system.")
    public ResponseEntity<List<RolUsuario>> ListarRoles() {
        List<RolUsuario> rolUsuarios = rolUsuarioService.getAllRoles();
        if (rolUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rolUsuarios);

    }

    @GetMapping("/RolUsuario/{id}")
    @Operation(summary = "Get user role by ID", description = "Returns a user role by its unique ID.")
    public ResponseEntity<RolUsuario> obtenerRolUsuario(@PathVariable Integer id) {
        RolUsuario rolUsuario = rolUsuarioService.getRolById(id);
        if (rolUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rolUsuario);
    }

    @PostMapping("/RolUsuario")
    @Operation(summary = "Create a new user role", description = "Creates a new user role with the provided details.")
    public ResponseEntity<RolUsuario> CrearRol(@RequestBody RolUsuario rolUsuario) {
        RolUsuario nuevoRolUsuario = rolUsuarioService.createRol(rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRolUsuario);
    }

    @PostMapping("/RolUsuario/{id}")
    @Operation(summary = "Update user role by ID", description = "Updates the details of an existing user role by its unique ID.")
    public ResponseEntity<RolUsuario> ActualizarRol(@PathVariable Integer id, @RequestBody RolUsuario rolUsuario) {
        try {

            RolUsuario rolUser = rolUsuarioService.getRolById(id);

            rolUser.setId_rol(rolUsuario.getId_rol());
            rolUser.setNombre_rol(rolUsuario.getNombre_rol());
            rolUser.setDescripcion_rol(rolUsuario.getDescripcion_rol());
            RolUsuario rolActualizado = rolUsuarioService.createRol(rolUser);
            return ResponseEntity.ok(rolActualizado);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/RolUsuario/{id}")
    @Operation(summary = "Delete user role by ID", description = "Deletes a user role from the system by its unique ID.")
    public ResponseEntity<?> eliminarRol(@PathVariable Integer id) {
        try {
            rolUsuarioService.deleteRol(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // ------- Controlador Evaluacion

    @GetMapping("/Evaluacion")
    @Operation(summary = "List all evaluations", description = "Returns a list of all evaluations available in the system.")
    public ResponseEntity<List<Evaluacion>> ListarEvaluacion() {
        List<Evaluacion> evaluaciones = evaluacionService.getAllEvaluacion();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/Evaluacion/{id}")
    @Operation(summary = "Get evaluation by ID", description = "Returns an evaluation by its unique ID.")
    public ResponseEntity<Evaluacion> obtenerEvaluacion(@PathVariable Integer id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionById(id);
        if (evaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evaluacion);
    }

    @PostMapping("/{id_user}/Evaluacion")
    @Operation(summary = "Create a new evaluation", description = "Creates a new evaluation with the provided details.")
    public ResponseEntity<Evaluacion> CrearEvaluacion(@PathVariable Integer id_user, @RequestBody Evaluacion evaluacion) {
        
        if(!usuarioService.userExist(id_user)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        // verificar que el usuario tenga rol de administrador o docente
        if(!usuarioService.getUserRole(id_user).equals("Administrador") || !usuarioService.getUserRole(id_user).equals("Docente")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }  
        
        Evaluacion nuevaEvaluacion = evaluacionService.createEvaluacion(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    @PostMapping("/{id_user}/Evaluacion/{id}")
    @Operation(summary = "Update evaluation by ID", description = "Updates the details of an existing evaluation by its unique ID.")
    public ResponseEntity<Evaluacion> ActualizarRol(@PathVariable Integer id_user, @PathVariable Integer id_evaluacion, @RequestBody Evaluacion evaluacion) {
        try {
        
        if(!usuarioService.userExist(id_user)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        // verificar que el usuario tenga rol de administrador o docente
        if(!usuarioService.getUserRole(id_user).equals("Administrador") || !usuarioService.getUserRole(id_user).equals("Docente")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }  
        
            Evaluacion eva = evaluacionService.getEvaluacionById(id_evaluacion);

            eva.setId_evaluacion(evaluacion.getId_evaluacion());
            eva.setTitulo(evaluacion.getTitulo());
            eva.setFechaEva(evaluacion.getFechaEva());
            eva.setDescripcionEva(evaluacion.getDescripcionEva());
            eva.setPuntajeMax(evaluacion.getPuntajeMax());
            Evaluacion evaActualizada = evaluacionService.createEvaluacion(evaluacion);
            return ResponseEntity.ok(evaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_user}/Evaluacion/{id}")
    @Operation(summary = "Delete evaluation by ID", description = "Deletes an evaluation from the system by its unique ID.")
    public ResponseEntity<?> eliminarEvaluacion(@PathVariable Integer id_user, @PathVariable Integer id_evaluacion) {
        try {
        
        if(!usuarioService.userExist(id_user)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        // verificar que el usuario tenga rol de administrador o docente
        if(!usuarioService.getUserRole(id_user).equals("Administrador") || !usuarioService.getUserRole(id_user).equals("Docente")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } 
            
            evaluacionService.deleteEvaluacion(id_evaluacion);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /// --------Controlador asignatura ( Actualizados )

    @GetMapping("/Asignatura")
    @Operation(summary = "List all subjects", description = "Returns a list of all subjects available in the system.")
    public ResponseEntity<List<Asignatura>> ListarAsignatura() {
        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
        if (asignaturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/Asignatura/{id}")
    @Operation(summary = "Get subject by ID", description = "Returns a subject by its unique ID.")
    public ResponseEntity<Asignatura> getAsignatura(@PathVariable Integer id) {
        Asignatura asignatura = asignaturaService.getAsignaturaById(id);
        if (asignatura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignatura);
    }

    @PostMapping("/{id_user}/Asignatura")
    @Operation(summary = "Create a new subject", description = "Creates a new subject with the provided details.")
    public ResponseEntity<Asignatura> CrearAsignatura(@PathVariable Integer id_user, @RequestBody Asignatura asignatura) {
        Usuario usuario = usuarioService.getUserById(id_user);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // verificar que el usuario tenga rol de administrador
        if (!usuario.getRol().getNombre_rol().equals("Administrador")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        Asignatura nuevaAsignatura = asignaturaService.createAsignatura(asignatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsignatura);
    }

    @PostMapping("/{id_user}/Asignatura/{id_asignatura}")
    @Operation(summary = "Update subject by ID", description = "Updates the details of an existing subject by its unique ID.")
    public ResponseEntity<Asignatura> ActualizarAsignatura(@PathVariable Integer id_user, @PathVariable Integer id_asignatura, @RequestBody Asignatura asignatura) {
        try {

            Usuario usuario = usuarioService.getUserById(id_user);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // verificar que el usuario tenga rol de administrador
            if (!usuario.getRol().getNombre_rol().equals("Administrador")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }

            Asignatura Asig = asignaturaService.getAsignaturaById(id_asignatura);

            Asig.setId_asignatura(asignatura.getId_asignatura());
            Asig.setNombre(asignatura.getNombre());
            Asig.setCosto(asignatura.getCosto());
            Asignatura asignaActualizada = asignaturaService.createAsignatura(asignatura);
            return ResponseEntity.ok(asignaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_user}/Asignatura/{id_asignatura}")
    @Operation(summary = "Delete subject by ID", description = "Deletes a subject from the system by its unique ID.")
    public ResponseEntity<?> eliminarAsignatura(@PathVariable Integer id_user, @PathVariable Integer id_asignatura) {
        try {

            Usuario usuario = usuarioService.getUserById(id_user);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            
            // verificar que el usuario tenga rol de administrador
            if (!usuario.getRol().getNombre_rol().equals("Administrador")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }

            asignaturaService.deleteAsignatura(id_asignatura);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    /// --------Controlador Boleta
    

    @GetMapping("/Boleta")
    @Operation(summary = "List all tickets", description = "Returns a list of all tickets (boletas) issued in the system.")
    public ResponseEntity<List<Boleta>> ListarBoletas() {
        List<Boleta> boletas = boletaService.getAllBoletas();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @GetMapping("/Boleta/{id}")
    @Operation(summary = "Get ticket by ID", description = "Returns a ticket (boleta) by its unique ID.")
    public ResponseEntity<Boleta> ObtenerBoleta(@PathVariable Integer id) {
        Boleta boleta = boletaService.getBoletaById(id);
        if (boleta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boleta);
    }       

    @PostMapping("/Boleta") 
    @Operation(summary = "Create a new ticket", description = "Creates a new ticket (boleta) with the provided details.")
    public ResponseEntity<Boleta> CrearBoleta(@RequestBody Boleta boleta) {
        Boleta nuevaBoleta = boletaService.createBoleta(boleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaBoleta);
    }   


    @PostMapping("/Boleta/{id}")
    @Operation(summary = "Update ticket by ID", description = "Updates the details of an existing ticket (boleta) by its unique ID.")
    public ResponseEntity<Boleta> ActualizarBoleta(@PathVariable Integer id, @RequestBody Boleta boleta) {
        try {

            Boleta bol = boletaService.getBoletaById(id);

            bol.setId_boleta(boleta.getId_boleta());
            bol.setUsuario(boleta.getUsuario());
            bol.setAsignatura(boleta.getAsignatura());
            bol.setFecha(boleta.getFecha());
            bol.setCupon(boleta.getCupon());
            bol.setMonto_total(boleta.getMonto_total());
            Boleta boletaActualizada = boletaService.createBoleta(boleta);
            return ResponseEntity.ok(boletaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/Boleta/{id}")
    @Operation(summary = "Delete ticket by ID", description = "Deletes a ticket (boleta) from the system by its unique ID.")
    public ResponseEntity<?> eliminarBoleta(@PathVariable Integer id) {
        try {
            boletaService.deleteBoleta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
