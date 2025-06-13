package com.Edu.EduTechInnovationSpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Edu.EduTechInnovationSpa.Model.RolUsuario;
import com.Edu.EduTechInnovationSpa.Service.RolUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/edu/RolUsuario")
@Tag(name = "EduTech API", description = "API for managing EduTech Innovation SPA") 

public class RolUsuarioController {
    @Autowired
    private RolUsuarioService rolUsuarioService;

    // TEST

    @GetMapping("/test/RolUsuario")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }

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


}
