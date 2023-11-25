package test;

import Encuestas.Encuesta;

import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.*;

import java.util.List;

public class EncuestaTest {

    @Test
    public void test00EncuestaSeCreaConNombre() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        Assertions.assertEquals("Encuesta de satisfaccion", encuesta.getNombre());
    }

    @Test
    public void test01EncuestaSeCreaConNombreVacioONulo() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Encuesta(""));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Encuesta(null));
    }

    @Test
    public void test02EncuestaSeCreaSinPreguntas() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        Assertions.assertEquals(0, encuesta.getPreguntasAbiertas().size());
        Assertions.assertEquals(0, encuesta.getPreguntasRespuestaUnica().size());
        Assertions.assertEquals(0, encuesta.getPreguntasRespuestaMultiple().size());
    }

    @Test
    public void test03EncuestaSeAgregaPreguntaAbierta() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        Assertions.assertEquals(1, encuesta.getPreguntasAbiertas().size());
        Assertions.assertEquals(0, encuesta.getPreguntasRespuestaUnica().size());
        Assertions.assertEquals(0, encuesta.getPreguntasRespuestaMultiple().size());
    }

    @Test
    public void test04EncuestaSeAgregaPreguntaRespuestaUnica() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        Assertions.assertEquals(0, encuesta.getPreguntasAbiertas().size());
        Assertions.assertEquals(1, encuesta.getPreguntasRespuestaUnica().size());
        Assertions.assertEquals(0, encuesta.getPreguntasRespuestaMultiple().size());
    }

    @Test
    public void test05EncuestaSeAgregaPreguntaRespuestaMultiple() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));
        Assertions.assertEquals(0, encuesta.getPreguntasAbiertas().size());
        Assertions.assertEquals(0, encuesta.getPreguntasRespuestaUnica().size());
        Assertions.assertEquals(1, encuesta.getPreguntasRespuestaMultiple().size());
    }

    @Test
    public void test06EncuestasSonIgualesSiTienenMismoNombreYPreguntas() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        encuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));

        Encuesta otraEncuesta = new Encuesta("Encuesta de satisfaccion");
        otraEncuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        otraEncuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        otraEncuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));

        Assertions.assertEquals(encuesta, otraEncuesta);
    }

    @Test
    public void test07EncuestasSonDistintasSiTienenDistintoNombre() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        Encuesta otraEncuesta = new Encuesta("Encuesta de opinion");
        Assertions.assertNotEquals(encuesta, otraEncuesta);
    }

    @Test
    public void test08EncuestasSonDistintasSiTienenDistintasPreguntas() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        encuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));

        Encuesta otraEncuesta = new Encuesta("Encuesta de satisfaccion");
        otraEncuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        otraEncuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        otraEncuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Reggaeton")));

        Assertions.assertNotEquals(encuesta, otraEncuesta);
    }

}
