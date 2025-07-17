package com.Edu.EduTechInnovationSpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Edu.EduTechInnovationSpa.Model.Cupon;
import com.Edu.EduTechInnovationSpa.Repository.CuponRepository;
import com.Edu.EduTechInnovationSpa.Service.CuponService;

@SpringBootTest
public class CuponServiceTest {

    @Autowired
    private CuponService cuponService;

    @MockBean
    private CuponRepository cuponMock;

    @Test
    public void testFindAll() {
        when(cuponMock.findAll()).thenReturn(List.of(new Cupon(1,"Code123",10, new Date(2023-01-01), new Date(2023-12-31), 4, 1)));

        List<Cupon> cupons = cuponService.getAllCupons();
        assertNotNull(cupons);
        assertEquals(1, cupons.size());
    }

    @Test 
    public void testFindById(){
        Integer id = 2;
        Cupon cupon = new Cupon(id, "Code123",10,new Date(2023-01-01), new Date(2023-12-31), 4,1);
        when (cuponMock.findById(id)).thenReturn(Optional.of(cupon));

        Cupon foundCupon = cuponService.getCuponById(id);
        assertNotNull(foundCupon);
        assertEquals(id, foundCupon.getId_cupon());
    }

    @Test
    public void testSave(){
        Cupon cupon = new Cupon(3, "Code456", 12, new Date(2023-02-01), new Date(2023-11-30), 4,1);
        when(cuponMock.save(cupon)).thenReturn(cupon);

        Cupon newCupon = cuponService.createCupon(cupon);
        assertNotNull(newCupon);
        assertEquals(3, newCupon.getId_cupon());
        }

    @Test
    public void testDelete(){
        Integer id = 2;
        doNothing().when(cuponMock).deleteById(id);
        cuponService.deleteCupon(id);

        verify(cuponMock, times(1)).deleteById(id);
    }
}

