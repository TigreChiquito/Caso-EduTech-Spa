package com.Edu.EduTechInnovationSpa.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.Edu.EduTechInnovationSpa.Controller.SeccionControllerV2;
import com.Edu.EduTechInnovationSpa.Model.Seccion;

@Component
public class SeccionModelAssembler implements RepresentationModelAssembler<Seccion, EntityModel<Seccion>> {
    @Override
    public EntityModel<Seccion> toModel(Seccion seccion) {
        return EntityModel.of(seccion,
                linkTo(methodOn(SeccionControllerV2.class).getSeccionById(seccion.getId_seccion())).withSelfRel(),
                linkTo(methodOn(SeccionControllerV2.class).getAllSeccions()).withRel("Seccions"));
    }
}
