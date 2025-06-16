package com.Edu.EduTechInnovationSpa.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

import com.Edu.EduTechInnovationSpa.Assemblers.UserModelAssembler;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Service.UserService;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<EntityModel<Usuario>> usuarios = userService.getAllUsers().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Usuario> getUserById(@PathVariable Integer id) {
        Usuario usuario = userService.getUserById(id);
        return assembler.toModel(usuario);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = userService.createUser(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).getUserById(newUsuario.getId_user())).toUri())
                .body(assembler.toModel(newUsuario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> updateUser(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId_user(id);
        Usuario updateUser = userService.createUser(usuario);
        return ResponseEntity.ok(assembler.toModel(updateUser));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
