package test;

import Builders.PreguntaBuilder;
import Builders.PreguntaCerradaBuilder;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreguntaBuilderTest {

    @Test
    void siendoPreguntaAbierta() {
        PreguntaAbierta preguntaAbierta = new PreguntaBuilder("¿Cual es tu nombre?").siendoPreguntaAbierta();
        assertEquals(PreguntaAbierta.class, preguntaAbierta.getClass());

    }

    @Test
    void siendoPreguntaCerradaRegresaObjeto() {
        List<String> opciones = Arrays.asList("Opción A", "Opción B", "Opción C");
        PreguntaCerradaBuilder preguntaCerradaBuilder = new PreguntaBuilder("Que opcion te gusta").siendoPreguntaCerrada(opciones);
        assertEquals(PreguntaCerradaBuilder.class, preguntaCerradaBuilder.getClass());


    }

    @Test
    void siendoPreguntaCerradaRespuestaUnica() {
        List<String> opciones = Arrays.asList("Opción A", "Opción B", "Opción C");
        PreguntaRespuestaUnica preguntaCerradaBuilder = new PreguntaBuilder("Que opcion te gusta").siendoPreguntaCerrada(opciones).conRespuestaUnica();
        assertEquals(PreguntaRespuestaUnica.class, preguntaCerradaBuilder.getClass());


    }

    @Test
    void siendoPreguntaCerradaRespuestaMultiple() {
        List<String> opciones = Arrays.asList("Opción A", "Opción B", "Opción C");
        PreguntaRespuestaMultiple preguntaCerradaBuilder = new PreguntaBuilder("Que opcion te gusta").siendoPreguntaCerrada(opciones).conRespuestaMultiple();
        assertEquals(PreguntaRespuestaMultiple.class, preguntaCerradaBuilder.getClass());

    }
}