package ar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Persona {

 private String nombre;
public String apellido;
 private List<Curso> cursos;


 public Persona(String nombre, String apellido) {

  var check = new NotNullNotEmpty("nombre", nombre, "apellido", apellido);
  check.throwOnError();

  this.nombre = nombre;
  this.apellido = apellido;
  this.cursos = new ArrayList<>();
 }

 public void addCurso(String nombre) {
  this.cursos.add(new Curso(nombre));
 }

 public void addCursos(String[] cursos){

  String[] curs = cursos;

  var tel = List.of(curs).stream().map((n) -> {
   return new Curso(n);
  }).collect(Collectors.toList());

  this.cursos.addAll(tel);
 }
 public void pisarCursos(String[] cursos){

  String[] curs = cursos;

  var tel = List.of(curs).stream().map((n) -> {
   return new Curso(n);
  }).collect(Collectors.toList());

  this.cursos = tel;
 }

 public String nombre() {
  return nombre + " " + apellido;
 }

 @Override
 public String toString() {
  return "Persona [nombre=" + nombre + ", apellido=" + apellido
    + ", cursos=" + cursos + "]";
 }

 public Map<String, Object> toMap() {
  var map = new HashMap<String, Object>(
    Map.of("nombre", nombre, "apellido", apellido));

  if (this.cursos != null && this.cursos.size() > 0) {
   map.put("cursos", cursos.stream().map((e) -> e.toMap())
     .collect(Collectors.toList()));
  }
  return map;
 }

 public boolean containsApellido(String apellido) {
  return this.apellido.contains(apellido);
 }
}
