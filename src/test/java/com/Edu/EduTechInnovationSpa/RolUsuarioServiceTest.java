package com.Edu.EduTechInnovationSpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Edu.EduTechInnovationSpa.Model.RolUsuario;
import com.Edu.EduTechInnovationSpa.Repository.RolUsuarioRepository;
import com.Edu.EduTechInnovationSpa.Service.RolUsuarioService;

@SpringBootTest
public class RolUsuarioServiceTest {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @MockBean
    private RolUsuarioRepository rolUsuarioRepository;

    @Test
    public void testFindAll() {
        when(rolUsuarioRepository.findAll()).thenReturn(List.of(new RolUsuario(4, "Extra", "Eres un extra")));

        List<RolUsuario> rolUsuarios = rolUsuarioService.getAllRoles();
        assertNotNull(rolUsuarios);
        assertEquals(1, rolUsuarios.size());
    }

    @Test
    public void testFindbyId() {
        Integer id = 4;
        RolUsuario rolUsuario = new RolUsuario(id, "Extra", "Eres un extra");
        when(rolUsuarioRepository.findById(id)).thenReturn(Optional.of(rolUsuario));

        RolUsuario rolFound = rolUsuarioService.getRolById(id);
        assertNotNull(rolFound);
        assertEquals(id, rolFound.getId_rol());
    }

    @Test
    public void testSaveRol() {
        RolUsuario rolUsuario = new RolUsuario(4, "Extra", "Eres un extra");
        when(rolUsuarioRepository.save(rolUsuario)).thenReturn(rolUsuario);

        RolUsuario saveRol = rolUsuarioService.createRol(rolUsuario);
        assertNotNull(saveRol);
        assertEquals("Extra", saveRol.getNombre_rol());
    }

    @Test
    public void testDeletebyId() {
        Integer id = 4;
        doNothing().when(rolUsuarioRepository).deleteById(id);
        rolUsuarioService.deleteRol(id);

        verify(rolUsuarioRepository, times(1)).deleteById(id);
        ;
    }
}
