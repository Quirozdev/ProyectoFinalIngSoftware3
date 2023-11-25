package test;

import Strategies.PreguntaRespuestaMultiple;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class PreguntaRespuestaMultipleTest {

    @Test
    public void test01PreguntaMultiple() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PreguntaRespuestaMultiple("¿que dia es hoy?",null));
    }
    @Test
    public void test02PreguntaMultiplelistavacia() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PreguntaRespuestaMultiple("¿que dia es hoy?",new ArrayList<>()));
    }
    @Test
    public void test0Preguntaenunciadovacio() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PreguntaRespuestaMultiple(null,new ArrayList<>(List.of("perro,gato,juan"))));
    }
    @Test
    public void test3Preguntaenunciadovacio() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PreguntaRespuestaMultiple(null, null));
    }
    @Test
    public void test04Respuestascorrectas() {
        PreguntaRespuestaMultiple preguntaRespuestaMultiple = new PreguntaRespuestaMultiple("¿que dia es?",new ArrayList<>(List.of("Lunes","Martes","Miercoles")));
      preguntaRespuestaMultiple.setSelectedRespuesta(List.of(1,3));
      Assertions.assertEquals(preguntaRespuestaMultiple.getSelectedRespuesta(),List.of(1,3));
    }
    @Test
    public void test05DosRespuestasIncorrectas() {
        PreguntaRespuestaMultiple preguntaRespuestaMultiple = new PreguntaRespuestaMultiple("¿que dia es?",new ArrayList<>(List.of("Lunes","Martes","Miercoles")));
        preguntaRespuestaMultiple.setSelectedRespuesta(List.of(1,3));
        Assertions.assertNotEquals(preguntaRespuestaMultiple.getSelectedRespuesta(),List.of(1,5));
    }
    @Test
    public void test06RangoInvalido() {
        PreguntaRespuestaMultiple preguntaRespuestaMultiple = new PreguntaRespuestaMultiple("¿que dia es?",new ArrayList<>(List.of("Lunes","Martes","Miercoles")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->preguntaRespuestaMultiple.setSelectedRespuesta(List.of(7,9)));
    }
    @Test
    public void test07Respuestavacia() {
        PreguntaRespuestaMultiple preguntaRespuestaMultiple = new PreguntaRespuestaMultiple("¿que dia es?",new ArrayList<>(List.of("Lunes","Martes","Miercoles")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->preguntaRespuestaMultiple.setSelectedRespuesta(List.of()));
    }
}
/*
public void test04RespuestasIncorrectas() {
        PreguntaRespuestaMultiple preguntaRespuestaMultiple = new PreguntaRespuestaMultiple("¿que dia es?",new ArrayList<>(List.of("Lunes","Martes","Miercoles")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> preguntaRespuestaMultiple.setSelectedRespuesta(List.of(1,3)));
    }
 */