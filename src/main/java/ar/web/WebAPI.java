package ar.web;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.model.Curso;
import ar.model.Persona;
import ar.model.PersonaException;
import ar.servicios.Cursos;
import ar.servicios.Personas;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebAPI {

 private Personas personas;
 private Cursos cursos;
 private int webPort;

 public WebAPI(Cursos cursos, Personas personas, int webPort) {
  this.personas = personas;
  this.cursos = cursos;
  this.webPort = webPort;
 }

 public void start() {
  Javalin app = Javalin.create(config -> {
   config.enableCorsForAllOrigins();
  }).start(this.webPort);
  app.get("/estudiantes", traerPersonas());
  app.get("/cursos", traerCursos());
  app.post("/estudiantes", crearPersona());


  app.exception(PersonaException.class, (e, ctx) -> {
   ctx.json(Map.of("result", "error", "errors", e.toMap()));
   // log error in a stream...
  });

  app.exception(Exception.class, (e, ctx) -> {
   ctx.json(Map.of("result", "error", "errors", "Ups... algo se rompió.: " + e.getMessage() + ctx.body()));
   // log error in a stream...
  });
 }

 private Handler traerCursos() {
  return ctx -> {
   var cursos = this.cursos.cursos();
   var list = new ArrayList<Map<String, Object>>();
   for (Curso l : cursos) {
    list.add(l.toMap());
   }
   ctx.json(Map.of("result", "success", "cursos", list));
  };
 }

 private Handler crearPersona() {

  return ctx -> {

   String apellido = ctx.queryParam("apellido");
   String body = ctx.body();

   System.out.println(apellido + " " + body);


    if(apellido == null || apellido.isEmpty()){
     PersonaDto dto = ctx.bodyAsClass(PersonaDto.class);

     System.out.println(apellido + " " + cursos);

     this.personas.crearPersona(dto.getNombre(), dto.getApellido(), dto.getCursos());
     ctx.json(Map.of("result", "success"));
    }

  else
   {
    PersonaDto dto = ctx.bodyAsClass(PersonaDto.class);
    this.personas.actualizar(apellido, dto.getCursos());
   }
  };
 }


 private Handler traerPersonas() {
  return ctx -> {
   String apellido = ctx.queryParam("apellido");
   List<Persona> personas = this.personas.personas(apellido);

   var list = new ArrayList<Map<String, Object>>();

   for (Persona p : personas) {
    list.add(p.toMap());
   }

   ctx.json(Map.of("result", "success", "estudiantes", list));

  };
 }
}