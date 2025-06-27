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

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Repository.AsignaturaRepository;
import com.Edu.EduTechInnovationSpa.Service.AsignaturaService;

@SpringBootTest
public class AsignaturaServiceTest {
    @Autowired
    private AsignaturaService asignaturaService;

    @MockBean
    private AsignaturaRepository asignaturaRepository;

    @Test
    public void testFindAll() {
        when(asignaturaRepository.findAll())
                .thenReturn(List.of(new Asignatura(2, "Fisica", "descripcion de asig.", 20)));

        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
        assertNotNull(asignaturas);
        assertEquals(1, asignaturas.size());
    }

    @Test
    public void testFindbyId() {
        Integer id = 2;
        Asignatura asignatura = new Asignatura(id, "Fisica", "descripcion de asig.", 20);
        when(asignaturaRepository.findById(id)).thenReturn(Optional.of(asignatura));

        Asignatura foundAsignatura = asignaturaService.getAsignaturaById(id);
        assertNotNull(foundAsignatura);
        assertEquals(id, foundAsignatura.getId_asignatura());
    }

    @Test
    public void testSave() {
        Asignatura asignatura = new Asignatura(2, "Fisica", "descripcion de asig.", 20);
        when(asignaturaRepository.save(asignatura)).thenReturn(asignatura);

        Asignatura saveAsignatura = asignaturaService.createAsignatura(asignatura);
        assertNotNull(saveAsignatura);
        assertEquals("Fisica", saveAsignatura.getNombre());
    }

    @Test
    public void testDeletebyId() {
        Integer id = 2;
        doNothing().when(asignaturaRepository).deleteById(id);
        asignaturaService.deleteAsignatura(id);

        verify(asignaturaRepository, times(1)).deleteById(id);
    }

}
