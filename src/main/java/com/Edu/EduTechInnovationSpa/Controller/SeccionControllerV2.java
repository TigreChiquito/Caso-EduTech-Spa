package com.Edu.EduTechInnovationSpa.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Edu.EduTechInnovationSpa.Assemblers.SeccionModelAssembler;
import com.Edu.EduTechInnovationSpa.Model.Seccion;
import com.Edu.EduTechInnovationSpa.Service.SeccionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v2/seccions")
public class SeccionControllerV2 {

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private SeccionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Seccion>> getAllSeccions() {
        List<EntityModel<Seccion>> seccions = seccionService.getAllSeccions().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(seccions, linkTo(methodOn(SeccionControllerV2.class).getAllSeccions()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Seccion> getSeccionById(@PathVariable Integer id) {
        Seccion seccion = seccionService.getSeccionById(id);
        return assembler.toModel(seccion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Seccion>> createSeccion(@RequestBody Seccion seccion) {
        Seccion newSeccion = seccionService.createSeccion(seccion);
        return ResponseEntity
                .created(linkTo(methodOn(SeccionControllerV2.class).getSeccionById(newSeccion.getId_seccion())).toUri())
                .body(assembler.toModel(newSeccion));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Seccion>> updateSeccion(@PathVariable Integer id, @RequestBody Seccion seccion) {
        seccion.setId_seccion(id);
        Seccion updateSeccion = seccionService.createSeccion(seccion);
        return ResponseEntity.ok(assembler.toModel(updateSeccion));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteSeccion(@PathVariable Integer id) {
        seccionService.deleteSeccion(id);
        return ResponseEntity.noContent().build();
    }

}
