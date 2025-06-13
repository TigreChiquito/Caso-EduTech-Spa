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

import com.Edu.EduTechInnovationSpa.Model.Seccion;
import com.Edu.EduTechInnovationSpa.Service.SeccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/edu/Seccion")
@Tag(name = "EduTech API", description = "API for managing EduTech Innovation SPA") 


public class SeccionController {
    @Autowired
    private SeccionService seccionService;

    // TEST

    @GetMapping("/test/Seccion")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }

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
}
