package com.portfolio.bav.Controller;



import com.portfolio.bav.Dto.dtoPersona;
import com.portfolio.bav.Entity.Persona;
import com.portfolio.bav.Security.Controller.Mensaje;
import com.portfolio.bav.Service.SPersona;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.bav.Entity.Persona;
import com.portfolio.bav.Service.SPersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("persona")
@CrossOrigin (origins = "https://frontendbav.web.app/")

public class CPersona {
    
    @Autowired
    SPersona sPersona;

    @GetMapping("/lista")
    public ResponseEntity<List< Persona>> list() {
        List<Persona> list = sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!sPersona.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Persona persona = sPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
   

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopers) {
        if (StringUtils.isBlank(dtopers.getNombreP())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
        }
        if (sPersona.existsByNombreP(dtopers.getNombreP())) {
            return new ResponseEntity(new Mensaje("Esa persona existe!"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = new Persona(dtopers.getNombreP(), dtopers.getDescP(), dtopers.getImgP(), dtopers.getTituloP(), dtopers.getImgPortada(), dtopers.getApellidoP());
        sPersona.save(persona);

        return new ResponseEntity(new Mensaje("Persona creada!"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopers) {
        //validamos ID
        if (!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe!"), HttpStatus.BAD_REQUEST);
        //compara nombres de experiencias
        if (sPersona.existsByNombreP(dtopers.getNombreP()) && sPersona.getByNombreP(dtopers.getNombreP()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa Persona ya existe!"), HttpStatus.BAD_REQUEST);
        ///// no puede estar vacio
        if (StringUtils.isBlank(dtopers.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
        

        Persona persona = sPersona.getOne(id).get();
        persona.setNombreP(dtopers.getNombreP());
        persona.setDescP(dtopers.getDescP());
        persona.setTituloP(dtopers.getTituloP());
        persona.setImgP(dtopers.getImgP());
        persona.setImgPortada(dtopers.getImgPortada());
        persona.setApellidoP(dtopers.getApellidoP());

        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona Actualizada"), HttpStatus.OK);
    }

}