package com.portfolio.bav.Service;

import com.portfolio.bav.Entity.Educacion;
import com.portfolio.bav.Repository.REducacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class SEducacion {
    @Autowired
    REducacion rEducacion;

    public List<Educacion> list() {
        return rEducacion.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return rEducacion.findById(id);
    }
    
   public Optional <Educacion> getByNombreE(String nombreE){
       return rEducacion.findByNombreEdu(nombreE);
   }
   
   public void save(Educacion edu) {
       rEducacion.save(edu);
   }
   
   public void delete(int id){
       rEducacion.deleteById(id);
   }
   
   public boolean existsById(int id){
       return rEducacion.existsById(id);
   }
   
   public boolean existsByNombreEdu(String nombreE){
       return rEducacion.existsByNombreEdu(nombreE);
   }
}