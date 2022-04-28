package ar.servicios;

import java.util.List;

import ar.model.Curso;
import ar.model.Persona;

public interface Personas {

 List<Persona> personas(String apellido);
  
 void crearPersona(String nombre, String apellido, String[] cursos);

 void actualizar(String apellido, String[] cursos);

}
