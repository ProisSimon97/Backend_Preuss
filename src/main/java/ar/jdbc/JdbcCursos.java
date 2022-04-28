package ar.jdbc;

import java.util.List;

import ar.model.Curso;
import ar.servicios.Cursos;

public class JdbcCursos implements Cursos {

 @Override
 public List<Curso> cursos() {
  return List.of(new Curso("Curso Laravel"), new Curso("Curso React"),
    new Curso("Curso C++"), new Curso("Curso Docker"));
 }

}
