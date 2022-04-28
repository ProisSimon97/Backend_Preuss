package ar.main;

import ar.jdbc.JdbcCursos;
import ar.jdbc.JdbcPersonas;
import ar.web.WebAPI;

public class Main {

 public static void main(String[] args) {
  WebAPI servicio = new WebAPI(new JdbcCursos(), new JdbcPersonas(),
    1234);
  servicio.start();
 }
}
