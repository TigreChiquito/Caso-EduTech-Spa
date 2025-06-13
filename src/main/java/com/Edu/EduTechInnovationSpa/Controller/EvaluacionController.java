package com.Edu.EduTechInnovationSpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.Edu.EduTechInnovationSpa.Model.Evaluacion;
import com.Edu.EduTechInnovationSpa.Service.EvaluacionService;
import com.Edu.EduTechInnovationSpa.Service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1/edu/evaluacion")
@Tag(name = "EduTech API: Evaluacion", description = "API for managing Test for EduTech Innovation SPA") 
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private UserService usuarioService;

    @GetMapping("/test/evaluacion")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }


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
}
