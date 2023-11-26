package test;

import Factories.PreguntaFactory;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreguntaFactoryTest {

    @Test
    public void testCrearPreguntaAbierta() {
        PreguntaAbierta pregunta = PreguntaFactory.crearPreguntaAbierta("Cual es tu nombre?");
        assertEquals(PreguntaAbierta.class, pregunta.getClass());
    }

    @Test
    public void testCrearPreguntaRespuestaUnica() {
        List<String> opciones = List.of("Opción 1", "Opción 2", "Opción 3");
        PreguntaRespuestaUnica pregunta = PreguntaFactory.crearPreguntaRespuestaUnica("Que opcion es la correcta", opciones);
        assertEquals(PreguntaRespuestaUnica.class, pregunta.getClass());
    }

    @Test
    public void testCrearPreguntaRespuestaMultiple() {
        List<String> opciones = List.of("Opción 1", "Opción 2", "Opción 3");
        PreguntaRespuestaMultiple pregunta = PreguntaFactory.crearPreguntaRespuestaMultiple("Que opciones son correctas", opciones);
        assertEquals(PreguntaRespuestaMultiple.class, pregunta.getClass());
    }
}