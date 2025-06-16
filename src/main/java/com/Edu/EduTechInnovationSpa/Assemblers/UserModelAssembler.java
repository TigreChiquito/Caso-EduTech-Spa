package com.Edu.EduTechInnovationSpa.Assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.Edu.EduTechInnovationSpa.Controller.UsuarioControllerV2;
import com.Edu.EduTechInnovationSpa.Model.Usuario;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUserById(usuario.getId_user())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withRel("Usuarios"));
    }
}
