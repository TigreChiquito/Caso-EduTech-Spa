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

import com.Edu.EduTechInnovationSpa.Model.Asignatura;
import com.Edu.EduTechInnovationSpa.Model.Boleta;
import com.Edu.EduTechInnovationSpa.Model.Cupon;
import com.Edu.EduTechInnovationSpa.Model.Usuario;
import com.Edu.EduTechInnovationSpa.Repository.BoletaRepository;
import com.Edu.EduTechInnovationSpa.Service.BoletaService;

@SpringBootTest (properties = "spring.config.location=classpath:application-test.properties")
public class BoletaServiceTest {

    @Autowired
    private BoletaService boletaService;

    @MockBean
    private BoletaRepository boletaMock;

    @Test
    public void testFindAll(){
        when(boletaMock.findAll()).thenReturn(List.of(new Boleta(2,new Usuario(), new Asignatura(), new Date(10/01/2023), new Cupon(), 100.0f)));

        List<Boleta> boletas = boletaService.getAllBoletas();
        assertNotNull(boletas);
        assertEquals(1, boletas.size());
    }

    @Test
    public void testFindById(){
        Integer id = 2;
        Boleta boleta = new Boleta(id, new Usuario(), new Asignatura(), new Date(10/01/2023), new Cupon(), 100.0f);
        when(boletaMock.findById(id)).thenReturn(Optional.of(boleta));

        Boleta foundBoleta = boletaService.getBoletaById(id);
        assertNotNull(foundBoleta);
        assertEquals(id, foundBoleta.getId_boleta());
    }

    @Test
    public void testSave(){
        Boleta boleta = new Boleta(1, new Usuario(), new Asignatura(), new Date(10/01/2023), new Cupon(), 100.0f);
        when(boletaMock.save(boleta)).thenReturn(boleta);

        Boleta newBoleta = boletaService.createBoleta(boleta);
        assertNotNull(newBoleta);
        assertEquals(1, newBoleta.getId_boleta());
    }

    @Test
    public void testDeletebyId(){
        Integer id = 4;
        doNothing().when(boletaMock).deleteById(id);
        boletaService.deleteBoleta(id);

        verify(boletaMock, times(1)).deleteById(id);
    }
}
