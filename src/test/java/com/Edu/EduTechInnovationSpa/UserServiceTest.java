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
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Repository.UserRepository;
import com.Edu.EduTechInnovationSpa.Service.UserService;

@SpringBootTest (properties = "spring.config.location=classpath:application-test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        when(userRepository.findAll()).thenReturn(
                List.of(new Usuario(10, "juan", "perez", "12754399-1", "jperez@ejemplo.cl", null, new RolUsuario())));

        List<Usuario> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void testFindbyId() {
        Integer id = 10;
        Usuario user = new Usuario(id, "juan", "perez", "12754399-1", "jperez@ejemplo.cl", null, new RolUsuario());
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Usuario foundUser = userService.getUserById(id);
        assertNotNull(foundUser);
        assertEquals(id, foundUser.getId_user());
    }

    @Test
    public void testSave() {
        Usuario user = new Usuario(10, "juan", "perez", "12754399-1", "jperez@ejemplo.cl", null, new RolUsuario());
        when(userRepository.save(user)).thenReturn(user);

        Usuario saveUser = userService.createUser(user);
        assertNotNull(saveUser);
        assertEquals("12754399-1", saveUser.getRun());
    }

    @Test
    public void testDeletebyId() {
        Integer id = 10;
        doNothing().when(userRepository).deleteById(id);
        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }

}
