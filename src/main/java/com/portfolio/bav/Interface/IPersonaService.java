package com.portfolio.bav.Interface;

import com.portfolio.bav.Entity.Persona;
import java.util.List;

import com.portfolio.bav.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Traer una lista de personas
    public List<Persona> getPersona();
    //Guardar un objeto de tipo persona
    public void savePersona (Persona persona);
    //Eliminar persona pero lo buscamos por ID
    public void deletePersona (int id);
    //Buscar una persona por ID
    public Persona findPersona (int id);
}