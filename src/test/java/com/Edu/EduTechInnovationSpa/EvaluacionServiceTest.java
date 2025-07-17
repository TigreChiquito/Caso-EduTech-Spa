package com.Edu.EduTechInnovationSpa;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.Edu.EduTechInnovationSpa.Model.Evaluacion;
import com.Edu.EduTechInnovationSpa.Model.Seccion;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.Edu.EduTechInnovationSpa.Repository.EvaluacionRepository;
import com.Edu.EduTechInnovationSpa.Service.EvaluacionService;

@SpringBootTest(properties = "spring.config.location=classpath:application-test.properties")
public class EvaluacionServiceTest {

    @Autowired
    private EvaluacionService evaluacionService;

    @MockBean
    private EvaluacionRepository evaluacionMock; 

    @Test
    public void testFindAll() {
        when(evaluacionMock.findAll()).thenReturn(List.of(new Evaluacion(1, "Evaluacion 1", new Date(2024, 5, 10), "Descripcion 1", 10, 8, new Usuario(), new Seccion())));

        List<Evaluacion> evaluaciones = evaluacionService.getAllEvaluacion();
        assertNotNull(evaluaciones);
        assertEquals(1, evaluaciones.size());
    }

    @Test
    public void testFindById() {
        Integer id = 2;
        Evaluacion evaluacion = new Evaluacion(id, "Evaluacion 2", new Date(2024, 5, 11), "Descripcion 2", 10, 9, new Usuario(), new Seccion());
        when(evaluacionMock.findById(id)).thenReturn(java.util.Optional.of(evaluacion));

        Evaluacion foundEvaluacion = evaluacionService.getEvaluacionById(id);
        assertNotNull(foundEvaluacion);
        assertEquals(id, foundEvaluacion.getId_evaluacion());
    }

    @Test
    public void testSave(){
        Evaluacion evaluacion = new Evaluacion(3, "Evaluacion 3", new Date(2024, 5, 12), "Descripcion 3", 10, 7, new Usuario(), new Seccion());
        when (evaluacionMock.save(evaluacion)).thenReturn(evaluacion);

        Evaluacion newEvaluacion = evaluacionService.createEvaluacion(evaluacion);
        assertNotNull(newEvaluacion);
        assertEquals("Evaluacion 3", newEvaluacion.getTitulo());
    }

    @Test
    public void testDelete() {
        Integer id = 3;
        doNothing().when(evaluacionMock).deleteById(id);
        evaluacionService.deleteEvaluacion(id);

        verify(evaluacionMock, times(1)).deleteById(id);
    }
}
