package test;

import java.util.Arrays;
import java.util.List;



import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class RespuestaUnicaTest {

    @Test
    public void testGetSelectedRespuesta() {
        List<String> opciones = Arrays.asList("Opción A", "Opción B", "Opción C");
        PreguntaRespuestaUnica pregunta = new PreguntaRespuestaUnica("¿Cuál es tu opción favorita?", opciones);

        assertNull(pregunta.getSelectedRespuesta());

        pregunta.setSelectedRespuesta(2);

        assertEquals(2, pregunta.getSelectedRespuesta());
    }

    @Test
    public void testSetSelectedRespuesta() {
        List<String> opciones = Arrays.asList("Opción A", "Opción B", "Opción C");
        PreguntaRespuestaUnica pregunta = new PreguntaRespuestaUnica("¿Cuál es tu opción favorita?", opciones);


        assertThrows(IllegalArgumentException.class, () -> pregunta.setSelectedRespuesta(0));
        assertThrows(IllegalArgumentException.class, () -> pregunta.setSelectedRespuesta(4));

        // Seleccionar una opción válida debería funcionar sin problemas
        pregunta.setSelectedRespuesta(2);
        assertEquals(2, pregunta.getSelectedRespuesta());
    }

    @Test
    public void testEquals() {
        List<String> opciones1 = Arrays.asList("Opción A", "Opción B", "Opción C");
        List<String> opciones2 = Arrays.asList("Opción A", "Opción B", "Opción C");

        PreguntaRespuestaUnica pregunta1 = new PreguntaRespuestaUnica("¿Cuál es tu opción favorita?", opciones1);
        PreguntaRespuestaUnica pregunta2 = new PreguntaRespuestaUnica("¿Cuál es tu opción favorita?", opciones2);

        assertEquals(pregunta1, pregunta2);

        // Cambiar una opción debería hacer que las preguntas no sean iguales
        opciones2.set(0, "Nueva Opción");
        assertNotEquals(pregunta1, pregunta2);
    }


    @Test
    public void testToString() {
        List<String> opciones = Arrays.asList("Opción A", "Opción B", "Opción C");
        PreguntaRespuestaUnica pregunta = new PreguntaRespuestaUnica("¿Cuál es tu opción favorita?", opciones);

        String expectedToString = "¿Cuál es tu opción favorita?\n" +
                "1. Opción A\n" +
                "2. Opción B\n" +
                "3. Opción C\n";

        assertEquals(expectedToString, pregunta.toString());
    }
    @Test
    public void testEqualsDifferentEnunciado() {
        List<String> opciones1 = Arrays.asList("Opción A", "Opción B", "Opción C");
        List<String> opciones2 = Arrays.asList("Opción A", "Opción B", "Opción C");

        PreguntaRespuestaUnica pregunta1 = new PreguntaRespuestaUnica("¿Cuál es tu opción favorita?", opciones1);
        PreguntaRespuestaUnica pregunta2 = new PreguntaRespuestaUnica("¿Cuál es tu opción preferida?", opciones2);

        // Dos preguntas con enunciados diferentes no deberían ser iguales
        assertNotEquals(pregunta1, pregunta2);
    }
}
