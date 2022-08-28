package com.portfolio.bav.Controller;

import com.portfolio.bav.Dto.dtoEducacion;
import com.portfolio.bav.Entity.Educacion;
import com.portfolio.bav.Security.Controller.Mensaje;
import com.portfolio.bav.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("edu")
@CrossOrigin(origins = "https://frontendbav.web.app/")
public class CEducacion {
        
    @Autowired
    SEducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List< Educacion>> list() {
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("Id no existe"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminadas"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu) {
        if (StringUtils.isBlank(dtoedu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existsByNombreEdu(dtoedu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("Esa educación existe!"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion (dtoedu.getNombreEdu(), dtoedu.getDescripcionEdu(), dtoedu.getFechaEdu());
        sEducacion.save(educacion);

        return new ResponseEntity(new Mensaje("Educación agregada!"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoedu) {
        //validamos ID
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe!"), HttpStatus.BAD_REQUEST);
        }
        //compara nombres de experiencias
        if (sEducacion.existsByNombreEdu(dtoedu.getNombreEdu()) && sEducacion.getByNombreE(dtoedu.getNombreEdu()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa educación ya existe"), HttpStatus.BAD_REQUEST);
        }
        // no puede estar vacio
        if (StringUtils.isBlank(dtoedu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEdu(dtoedu.getNombreEdu());
        educacion.setDescripcionEdu(dtoedu.getDescripcionEdu());
        educacion.setFechaEdu(dtoedu.getFechaEdu());

        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educación Actualizada"), HttpStatus.OK);
    }

    
}