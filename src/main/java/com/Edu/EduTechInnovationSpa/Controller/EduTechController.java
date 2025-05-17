package com.Edu.EduTechInnovationSpa.Controller;

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Model.Cupon;
import com.Edu.EduTechInnovationSpa.Model.Seccion;
import com.Edu.EduTechInnovationSpa.Model.Evaluacion;
import com.Edu.EduTechInnovationSpa.Model.RolUsuario;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/edu")
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
    public ResponseEntity<List<Usuario>> ListarUsuarios() {

        List<Usuario> usuarios = usuarioService.getAllUsers();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/Usuarios/{id}")
    public ResponseEntity<Usuario> ObtenerUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUserById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/Usuarios")
    public ResponseEntity<Usuario> CrearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.createUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/Usuarios/{id}")
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
    public ResponseEntity<List<Seccion>> ListarCursos() {
        List<Seccion> secciones = seccionService.getAllSeccions();
        if (secciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(secciones);
    }

    @GetMapping("/Secciones/{id}")
    public ResponseEntity<Seccion> getSecciones(@PathVariable int id) {
        Seccion seccion = seccionService.getSeccionById(id);
        if (seccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(seccion);

    }

    @PostMapping("/Seccion")
    public ResponseEntity<Seccion> CrearSeccion(@RequestBody Seccion seccion) {
        Seccion newSeccion = seccionService.createSeccion(seccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSeccion);
    }

    @PostMapping("/Seccion/{id}")
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
    public ResponseEntity<List<Cupon>> ListarCupones() {
        List<Cupon> cupones = cuponService.getAllCupons();
        if (cupones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cupones);
    }

    // --------Controlador RolUsuario

    @GetMapping("/RolUsuario")
    public ResponseEntity<List<RolUsuario>> ListarRoles() {
        List<RolUsuario> rolUsuarios = rolUsuarioService.getAllRoles();
        if (rolUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rolUsuarios);

    }

    @GetMapping("/RolUsuario/{id}")
    public ResponseEntity<RolUsuario> obtenerRolUsuario(@PathVariable Integer id) {
        RolUsuario rolUsuario = rolUsuarioService.getRolById(id);
        if (rolUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rolUsuario);
    }

    @PostMapping("/RolUsuario")
    public ResponseEntity<RolUsuario> CrearRol(@RequestBody RolUsuario rolUsuario) {
        RolUsuario nuevoRolUsuario = rolUsuarioService.createRol(rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRolUsuario);
    }

    @PostMapping("/RolUsuario/{id}")
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
    public ResponseEntity<List<Evaluacion>> ListarEvaluacion() {
        List<Evaluacion> evaluaciones = evaluacionService.getAllEvaluacion();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/Evaluacion/{id}")
    public ResponseEntity<Evaluacion> obtenerEvaluacion(@PathVariable Integer id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionById(id);
        if (evaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evaluacion);
    }

    @PostMapping("/Evaluacion")
    public ResponseEntity<Evaluacion> CrearEvaluacion(@RequestBody Evaluacion evaluacion) {
        Evaluacion nuevaEvaluacion = evaluacionService.createEvaluacion(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    @PostMapping("/Evaluacion/{id}")
    public ResponseEntity<Evaluacion> ActualizarRol(@PathVariable Integer id, @RequestBody Evaluacion evaluacion) {
        try {

            Evaluacion eva = evaluacionService.getEvaluacionById(id);

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

    @DeleteMapping("/Evaluacion/{id}")
    public ResponseEntity<?> eliminarEvaluacion(@PathVariable Integer id) {
        try {
            evaluacionService.deleteEvaluacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /// --------Controlador asignatura

    @GetMapping("/Asignatura")
    public ResponseEntity<List<Asignatura>> ListarAsignatura() {
        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
        if (asignaturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/Asignatura/{id}")
    public ResponseEntity<Asignatura> getAsignatura(@PathVariable Integer id) {
        Asignatura asignatura = asignaturaService.getAsignaturaById(id);
        if (asignatura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignatura);
    }

    @PostMapping("/Asignatura")
    public ResponseEntity<Asignatura> CrearAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura nuevaAsignatura = asignaturaService.createAsignatura(asignatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsignatura);
    }

    @PostMapping("/Asignatura/{id}")
    public ResponseEntity<Asignatura> ActualizarAsignatura(@PathVariable Integer id,
            @RequestBody Asignatura asignatura) {
        try {

            Asignatura Asig = asignaturaService.getAsignaturaById(id);

            Asig.setId_asignatura(asignatura.getId_asignatura());
            Asig.setNombre(asignatura.getNombre());
            Asig.setCosto(asignatura.getCosto());
            Asignatura asignaActualizada = asignaturaService.createAsignatura(asignatura);
            return ResponseEntity.ok(asignaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/Asignatura/{id}")
    public ResponseEntity<?> eliminarAsignatura(@PathVariable Integer id) {
        try {
            asignaturaService.deleteAsignatura(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
