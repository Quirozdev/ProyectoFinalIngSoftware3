package test;

import Encuestas.Encuesta;
import Encuestas.EncuestaContestable;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.*;

import java.util.List;

public class EncuestaContestableTest {
    private Encuesta encuesta;

    @BeforeEach
    public void setUp() {
        this.encuesta = new Encuesta("Encuesta de satisfaccion");
        this.encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        this.encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        this.encuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));
    }

    @Test
    public void test00EncuestaContestableSeCreaConEncuesta() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        Assertions.assertEquals(this.encuesta, encuestaContestable.getEncuesta());
    }

    @Test
    public void test01EncuestaContestableSeContestaPreguntaAbierta() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        encuestaContestable.contestarPreguntaAbierta(0, "Juan");
        Assertions.assertEquals("Juan", encuestaContestable.getPreguntasAbiertas().get(0).getSelectedRespuesta());
    }

    @Test
    public void test02EncuestaContestableSeContestaPreguntaRespuestaUnica() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        encuestaContestable.contestarPreguntaRespuestaUnica(0, 1);
        Assertions.assertEquals(1, encuestaContestable.getPreguntasRespuestaUnica().get(0).getSelectedRespuesta());
    }

    @Test
    public void test03EncuestaContestableSeContestaPreguntaRespuestaMultiple() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        encuestaContestable.contestarPreguntaRespuestaMultiple(0, List.of(1, 2));
        Assertions.assertEquals(List.of(1, 2), encuestaContestable.getPreguntasRespuestaMultiple().get(0).getSelectedRespuesta());
    }

    @Test
    public void test04EncuestaContestableNoSeContestaPreguntaAbiertaConRespuestaVaciaONula() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> encuestaContestable.contestarPreguntaAbierta(0, ""));

        Assertions.assertNull(encuestaContestable.getPreguntasAbiertas().get(0).getSelectedRespuesta());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> encuestaContestable.contestarPreguntaAbierta(0, null));

        Assertions.assertNull(encuestaContestable.getPreguntasAbiertas().get(0).getSelectedRespuesta());

    }

    @Test
    public void test05EncuestaContestableNoSeContestaPreguntaRespuestaUnicaConRespuestaInvalida() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> encuestaContestable.contestarPreguntaRespuestaUnica(0, 5));

        Assertions.assertNull(encuestaContestable.getPreguntasRespuestaUnica().get(0).getSelectedRespuesta());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> encuestaContestable.contestarPreguntaRespuestaUnica(0, 0));

        Assertions.assertNull(encuestaContestable.getPreguntasRespuestaUnica().get(0).getSelectedRespuesta());
    }

    @Test
    public void test06EncuestaContestableNoSeContestaPreguntaRespuestaMultipleConRespuestaInvalida() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> encuestaContestable.contestarPreguntaRespuestaMultiple(0, List.of(5, 6)));

        Assertions.assertNull(encuestaContestable.getPreguntasRespuestaMultiple().get(0).getSelectedRespuesta());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> encuestaContestable.contestarPreguntaRespuestaMultiple(0, List.of(0, -1)));

        Assertions.assertNull(encuestaContestable.getPreguntasRespuestaMultiple().get(0).getSelectedRespuesta());
    }

    @Test
    public void test07EncuestaContestableHaSidoContestadaCompletamente() {
        EncuestaContestable encuestaContestable = new EncuestaContestable(this.encuesta);
        Assertions.assertFalse(encuestaContestable.haSidoContestadaCompletamente());

        encuestaContestable.contestarPreguntaAbierta(0, "Juan");
        Assertions.assertFalse(encuestaContestable.haSidoContestadaCompletamente());

        encuestaContestable.contestarPreguntaRespuestaUnica(0, 1);
        Assertions.assertFalse(encuestaContestable.haSidoContestadaCompletamente());

        encuestaContestable.contestarPreguntaRespuestaMultiple(0, List.of(1, 2));
        Assertions.assertTrue(encuestaContestable.haSidoContestadaCompletamente());
    }

}
