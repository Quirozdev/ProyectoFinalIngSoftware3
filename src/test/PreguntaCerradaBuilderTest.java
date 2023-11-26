package test;

import Builders.PreguntaCerradaBuilder;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PreguntaCerradaBuilderTest {
    @Test
    public void testConRespuestaUnica() {
        PreguntaCerradaBuilder builder = new PreguntaCerradaBuilder("¿Cuál es la capital de Francia?", Arrays.asList("París", "Londres", "Madrid"));
        PreguntaRespuestaUnica pregunta = builder.conRespuestaUnica();
        assertEquals(PreguntaRespuestaUnica.class, pregunta.getClass());
    }

    @Test
    public void testConRespuestaMultiple() {
        PreguntaCerradaBuilder builder = new PreguntaCerradaBuilder("¿Cuáles son las capitales de Europa?", Arrays.asList("París", "Londres", "Madrid", "Berlín"));
        PreguntaRespuestaMultiple pregunta = builder.conRespuestaMultiple();
        assertEquals(PreguntaRespuestaMultiple.class, pregunta.getClass());
    }


}