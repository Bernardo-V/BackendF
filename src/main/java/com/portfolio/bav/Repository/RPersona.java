package com.portfolio.bav.Repository;


import com.portfolio.bav.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.bav.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPersona extends JpaRepository<Persona, Integer>{
    public Optional<Persona> findByNombreP(String nombreP);
    public boolean existsByNombreP(String nombreP);
}
