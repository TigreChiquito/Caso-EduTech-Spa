package com.Edu.EduTechInnovationSpa.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Controller.AsignaturaControllerV2;

@Component
public class AsignaturaModelAssembler implements RepresentationModelAssembler<Asignatura, EntityModel<Asignatura>> {
    @Override
    public EntityModel<Asignatura> toModel(Asignatura asignatura) {
        return EntityModel.of(asignatura,
                linkTo(methodOn(AsignaturaControllerV2.class).getAsignaturaById(asignatura.getId_asignatura()))
                        .withSelfRel(),
                linkTo(methodOn(AsignaturaControllerV2.class).getAllAsignaturas()).withRel("asignaturas"));
    }
}
