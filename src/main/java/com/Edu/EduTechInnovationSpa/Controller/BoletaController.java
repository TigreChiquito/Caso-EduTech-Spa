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

import com.Edu.EduTechInnovationSpa.Model.Boleta;
import com.Edu.EduTechInnovationSpa.Service.BoletaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/edu/Boleta")
@Tag(name = "EduTech API", description = "API for managing EduTech Innovation SPA") 

public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    // TEST
    @GetMapping("/test/RolUsuario")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }

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
