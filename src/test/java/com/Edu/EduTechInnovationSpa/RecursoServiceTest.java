package com.Edu.EduTechInnovationSpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Edu.EduTechInnovationSpa.Model.Recurso;
import com.Edu.EduTechInnovationSpa.Repository.RecursoRepository;
import com.Edu.EduTechInnovationSpa.Service.RecursoService;

@SpringBootTest
public class RecursoServiceTest {

    @Autowired
    RecursoService recursoService;

    @MockBean
    RecursoRepository recursoMock;
    
    @Test
    public void testFindAll(){
        when(recursoMock.findAll()).thenReturn(List.of(new Recurso(1,"Recurso test",2,"https://test:com",new Date(2023-01-01))));

        List<Recurso> recursos = recursoService.getAllRecursos();
        assertNotNull(recursos);
        assertEquals(1, recursos.size());
    }

    @Test
    public void testFindById(){
        Integer id = 2;
        Recurso recurso = new Recurso(id, "Recurso test", 2, "https://test:com", new Date(2023-01-01));

        when(recursoMock.findById(id)).thenReturn(Optional.of(recurso));
        
        Recurso foundRecurso = recursoService.getRecursoById(id);
        assertNotNull(foundRecurso);
        assertEquals(id, foundRecurso.getId_recurso());
    }

    @Test
    public void testSave(){
        Recurso recurso = new Recurso(2,"Recurso test", 2, "https://test:com", new Date(2023-01-01));
        when(recursoMock.save(recurso)).thenReturn(recurso);

        Recurso saveRecurso = recursoService.createRecurso(recurso);
        assertNotNull(saveRecurso);
        assertEquals(2, saveRecurso.getId_recurso());
        
    }

    @Test
    public void testDeletebyId(){
        Integer id = 3;
        doNothing().when(recursoMock).deleteById(id);
        recursoService.deleteRecurso(id);

        verify(recursoMock,times(1)).deleteById(id);
    }
}
