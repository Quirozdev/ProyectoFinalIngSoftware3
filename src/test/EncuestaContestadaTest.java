package test;

import Encuestas.Encuesta;
import Encuestas.EncuestaContestable;
import Encuestas.EncuestaContestada;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.*;

import java.util.List;

public class EncuestaContestadaTest {

    @Test
    public void test00EncuestaContestadaSeCreaCuandoSeContestanTodasLasPreguntas() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        encuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));
        EncuestaContestable encuestaContestable = new EncuestaContestable(encuesta);
        encuestaContestable.contestarPreguntaAbierta(0, "Juan");
        encuestaContestable.contestarPreguntaRespuestaUnica(0, 1);
        encuestaContestable.contestarPreguntaRespuestaMultiple(0, List.of(1, 2));
        Assertions.assertDoesNotThrow(() -> new EncuestaContestada(encuestaContestable));
    }

    @Test
    public void test01EncuestaContestadaNoSeCreaCuandoFaltanPreguntasAbiertasPorResponder() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        EncuestaContestable encuestaContestable = new EncuestaContestable(encuesta);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EncuestaContestada(encuestaContestable));
    }

    @Test
    public void test02EncuestaContestadaNoSeCreaCuandoFaltanPreguntasRespuestaUnicaPorResponder() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        EncuestaContestable encuestaContestable = new EncuestaContestable(encuesta);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EncuestaContestada(encuestaContestable));
    }

    @Test
    public void test03EncuestaContestadaNoSeCreaCuandoFaltanPreguntasRespuestaMultiplePorResponder() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));
        EncuestaContestable encuestaContestable = new EncuestaContestable(encuesta);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EncuestaContestada(encuestaContestable));
    }
}
