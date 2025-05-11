package com.Edu.EduTechInnovationSpa.Controller;

import com.Edu.EduTechInnovationSpa.Model.Cupon;
import com.Edu.EduTechInnovationSpa.Model.Curso;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/edu")
public class EduTechController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UserService usuarioService;

    @Autowired
    private CuponService cuponService;

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

    // Controlador RolUsuario
    @GetMapping("")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
