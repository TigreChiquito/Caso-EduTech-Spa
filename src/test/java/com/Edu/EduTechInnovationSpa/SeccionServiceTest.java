package com.Edu.EduTechInnovationSpa;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Model.Seccion;
import com.Edu.EduTechInnovationSpa.Service.SeccionService; 

@SpringBootTest(properties = "spring.config.location=classpath:application-test.properties")
public class SeccionServiceTest {

    @Autowired
    private SeccionService seccionService;

    @MockBean
    private SeccionService seccionServiceMock;

    @Test
    public void testFindAll() {
        when(seccionServiceMock.getAllSeccions()).thenReturn(List.of(
            new Seccion(1, 30, "Docente A", new Date(10/01/2023), new Date(10/02/2023), new Asignatura())));

        List<Seccion> seccions = seccionService.getAllSeccions();
        assertNotNull(seccions);
        assertEquals(1, seccions.size()); 
        
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Seccion seccion = new Seccion(id, 30, "Docente A", new Date(10/01/2023), new Date(10/02/2023), new Asignatura());
        when(seccionServiceMock.getSeccionById(id)).thenReturn(seccion);

        Seccion foundSeccion = seccionService.getSeccionById(id);
        assertNotNull(foundSeccion);
        assertEquals(id, foundSeccion.getId_seccion());
    }

    @Test
    public void testSave() {
        Seccion seccion = new Seccion(1, 30, "Docente A", new Date(10/01/2023), new Date(10/02/2023), new Asignatura());
        when(seccionServiceMock.createSeccion(seccion)).thenReturn(seccion);

        Seccion savedSeccion = seccionService.createSeccion(seccion);
        assertNotNull(savedSeccion);
        assertEquals(seccion.getId_seccion(), savedSeccion.getId_seccion());
    }

    @Test
    public void testDeletebyId() {
        Integer id = 1;
        doNothing().when(seccionServiceMock).deleteSeccion(id);
        seccionService.deleteSeccion(id);

        verify(seccionServiceMock).deleteSeccion(id);
    }
}
