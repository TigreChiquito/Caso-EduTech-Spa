package com.Edu.EduTechInnovationSpa.Controller;

import com.Edu.EduTechInnovationSpa.Model.Cupon;
import com.Edu.EduTechInnovationSpa.Model.Curso;
import com.Edu.EduTechInnovationSpa.Model.RolUsuario;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/edu")
public class EduTechController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UserService usuarioService;

    @Autowired
    private CuponService cuponService;

    @Autowired
    private RolUsuarioService rolUsuarioService;

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
            user.setId_role(usuario.getId_role());
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

    // - - - - - - - - - - Controladores de Curso - - - - - - - - - -

    @GetMapping("/Cursos")
    public ResponseEntity<List<Curso>> ListarCursos() {
        List<Curso> cursos = cursoService.getAllCursos();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
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
        List<RolUsuario> rolUsuarios = rolUsuarioService.getAllUsers();
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
        RolUsuario nuevoRolUsuario = rolUsuarioService.createRolUsuario(rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRolUsuario);
    }

    @PostMapping("/RolUsuario/{id}")
    public ResponseEntity<RolUsuario> ActualizarRol(@PathVariable Integer id, @RequestBody RolUsuario rolUsuario) {
        try {

            RolUsuario rolUser = rolUsuarioService.getRolById(id);

            rolUser.setId_rol(rolUsuario.getId_rol());
            rolUser.setTipo_rol(rolUsuario.getTipo_rol());
            rolUser.setDescripcion_rol(rolUsuario.getDescripcion_rol());
            RolUsuario rolActualizado = rolUsuarioService.createRolUsuario(rolUser);
            return ResponseEntity.ok(rolActualizado);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/RolUsuario/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Integer id) {
        try {
            rolUsuarioService.deleteRolUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
