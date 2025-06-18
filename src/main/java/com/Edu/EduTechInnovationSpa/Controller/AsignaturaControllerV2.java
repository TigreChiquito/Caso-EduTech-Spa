package com.Edu.EduTechInnovationSpa.Controller;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Edu.EduTechInnovationSpa.Assemblers.AsignaturaModelAssembler;
import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Service.AsignaturaService;

@RestController
@RequestMapping("/api/v2/asignaturas")
public class AsignaturaControllerV2 {
    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private AsignaturaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Asignatura>> getAllAsignaturas() {
        List<EntityModel<Asignatura>> asignaturas = asignaturaService.getAllAsignaturas().stream()
                .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(asignaturas,
                linkTo(methodOn(AsignaturaControllerV2.class).getAllAsignaturas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Asignatura> getAsignaturaById(@PathVariable Integer id) {
        Asignatura asignatura = asignaturaService.getAsignaturaById(id);
        return assembler.toModel(asignatura);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Asignatura>> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura newAsignatura = asignaturaService.createAsignatura(asignatura);
        return ResponseEntity.created(
                linkTo(methodOn(AsignaturaControllerV2.class).getAsignaturaById(newAsignatura.getId_asignatura()))
                        .toUri())
                .body(assembler.toModel(newAsignatura));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Asignatura>> updateAsignatura(@PathVariable Integer id,
            @RequestBody Asignatura asignatura) {
        asignatura.setId_asignatura(id);
        Asignatura updateAsignatura = asignaturaService.createAsignatura(asignatura);
        return ResponseEntity.ok(assembler.toModel(updateAsignatura));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAsignatura(@PathVariable Integer id) {
        asignaturaService.deleteAsignatura(id);
        return ResponseEntity.noContent().build();
    }
}
