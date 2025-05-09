package com.Edu.EduTechInnovationSpa.Service;

import com.Edu.EduTechInnovationSpa.Model.Cupon;
import com.Edu.EduTechInnovationSpa.Repository.CuponRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Transactional
public class CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    public List<Cupon> getAllCupons() {
        return cuponRepository.findAll();
    }
    
    public Cupon getCuponById(Integer id) {
        return cuponRepository.findById(id).orElse(null);
    }

    public Cupon createCupon(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    public void deleteCupon(Integer id) {
        cuponRepository.deleteById(id);
    }

}
