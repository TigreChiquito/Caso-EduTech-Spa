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

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Service.AsignaturaService;
import com.Edu.EduTechInnovationSpa.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/edu/Asignatura")
@Tag(name = "EduTech API", description = "API for managing EduTech Innovation SPA") 
public class AsignaturaController {
    
     // TEST

    @GetMapping("/test/Asignatura")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private UserService usuarioService;

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
}
