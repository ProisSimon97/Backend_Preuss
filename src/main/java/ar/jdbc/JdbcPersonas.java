package ar.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.model.Curso;
import ar.model.Persona;
import ar.servicios.Cursos;
import ar.servicios.Personas;

public class JdbcPersonas implements Personas {

 //constructor con dependencias
 private List<Persona> estudiantes = new ArrayList<Persona>();

 public JdbcPersonas() {
  Persona p1 = new Persona("Joaquin", "Garcia");
  Persona p2 = new Persona("Emilio", "Peroz");
  Persona p3 = new Persona("Ernesto", "Perez");

  p1.addCurso("Curso C++");
  p1.addCurso("Curso Docker");
  p2.addCurso("Curso React");
  p3.addCurso("Curso Laravel");

  var personas = new ArrayList(List.of(p1, p2, p3));

  this.estudiantes = personas;
 }

 @Override
 public List<Persona> personas(String apellido) {

  if (apellido == null || apellido.isEmpty())
   return this.estudiantes;
  
  return this.estudiantes.stream().filter((p) -> {
   return p.containsApellido(apellido);
  }).collect(Collectors.toList());
 }

 @Override
 public void crearPersona(String nombre, String apellido, String[] cursos) {

  Persona p = new Persona(nombre, apellido);
  p.addCursos(cursos);

  this.estudiantes.add(p);

  System.out.println(p.toString());
 }

 @Override
 public void actualizar(String apellido, String[] cursos) {

  for (Persona p : this.estudiantes){

   if(p.apellido.equals(apellido)){
    p.pisarCursos(cursos);
    System.out.println(p.toString());
    break;
   }
  }
 }
}
