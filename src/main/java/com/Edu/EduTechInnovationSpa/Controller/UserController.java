package com.Edu.EduTechInnovationSpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1/edu/user")
@Tag(name = "EduTech API: Usuario", description = "API for managing users for EduTech Innovation SPA") 
public class UserController {

    @Autowired
    private UserService usuarioService;

    @GetMapping("/test/user")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }

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

}
