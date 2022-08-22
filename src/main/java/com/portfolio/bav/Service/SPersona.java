package com.portfolio.bav.Service;

import com.portfolio.bav.Entity.Persona;
import com.portfolio.bav.Repository.RPersona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.bav.Entity.Persona;
import com.portfolio.bav.Repository.RPersona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SPersona {
    @Autowired
    RPersona rPersona;
    
    public List <Persona> list(){
        return rPersona.findAll();
    }
    
    public Optional<Persona> getOne (int id){
        return rPersona.findById(id);
    }
    
    public Optional<Persona> getByNombreP(String nompreP){
        return rPersona.findByNombreP(nompreP);
    }
    
    public void save (Persona pers){
        rPersona.save(pers);
    }
    
    public boolean existsById(int id){
        return rPersona.existsById(id);
    }
    
    public boolean existsByNombreP(String nombreP){
        return rPersona.existsByNombreP(nombreP);
    }
}