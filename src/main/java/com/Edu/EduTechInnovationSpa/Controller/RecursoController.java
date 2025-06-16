package com.Edu.EduTechInnovationSpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.Edu.EduTechInnovationSpa.Model.*;
import com.Edu.EduTechInnovationSpa.Service.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/edu/recurso")
@Tag(name = "EduTech API: Recurso", description = "API for managing resources of EduTech Innovation SPA")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @Autowired
    private UserService usuarioService;

    @GetMapping("/test/recurso")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }

    @GetMapping("/Recurso")
    public ResponseEntity<List<Recurso>> ListarRecursos() {
        List<Recurso> recurso = recursoService.getAllRecursos();
        if (recurso.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/Recurso/{id}")
    public ResponseEntity<Recurso> ObtenerRecurso(@PathVariable Integer id) {
        Recurso recurso = recursoService.getRecursoById(id);
        if (recurso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recurso);
    }

    @PostMapping("/{id_user}/Recurso")
    public ResponseEntity<Recurso> CrearRecurso(@PathVariable Integer id_user, @RequestBody Recurso recurso) {

        if (!usuarioService.userExist(id_user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (!usuarioService.getUserRole(id_user).equals("Docente")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Recurso nuevoRecurso = recursoService.createRecurso(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRecurso);
    }

    @PostMapping("/{id_user}/Recurso/{id_recurso}")
    public ResponseEntity<Recurso> ActualizarRecurso(@PathVariable Integer id_user, @PathVariable Integer id_recurso,
            @RequestBody Recurso recurso) {

        try {

            if (!usuarioService.userExist(id_user)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (!usuarioService.getUserRole(id_user).equals("Docente")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }

            Recurso recu = recursoService.getRecursoById(id_recurso);

            recu.setId_recurso(recurso.getId_recurso());
            recu.setId_asignatura(recurso.getId_asignatura());
            recu.setNombre(recurso.getNombre());
            recu.setFecha(recurso.getFecha());
            recu.setVinculo_recurso(recurso.getVinculo_recurso());

            Recurso recuAct = recursoService.createRecurso(recurso);
            return ResponseEntity.ok(recuAct);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping("/{id_user}/Recurso/{id_recurso}")
    public ResponseEntity<?> eliminarRecurso(@PathVariable Integer id_user, @PathVariable Integer id_recurso,
            @RequestBody Recurso recurso) {

        try {

            if (!usuarioService.userExist(id_user)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (!usuarioService.getUserRole(id_user).equals("Docente")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }

            recursoService.deleteRecurso(id_recurso);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }

    }

}
