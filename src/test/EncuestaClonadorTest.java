package test;

import Encuestas.Encuesta;
import Encuestas.EncuestaClonador;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.*;

import java.util.List;

public class EncuestaClonadorTest {

    @Test
    public void test00EncuestaClonadorClonaCorrectamenteUnaEncuesta() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));
        encuesta.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus gustos musicales?", List.of("Rock", "Pop", "Jazz")));

        Encuesta encuestaClon = EncuestaClonador.clonar(encuesta);

        Assertions.assertEquals(encuesta, encuestaClon);
        Assertions.assertNotSame(encuesta, encuestaClon);

        Assertions.assertNull(encuestaClon.getPreguntasAbiertas().get(0).getSelectedRespuesta());
        Assertions.assertNull(encuestaClon.getPreguntasRespuestaUnica().get(0).getSelectedRespuesta());
        Assertions.assertNull(encuestaClon.getPreguntasRespuestaMultiple().get(0).getSelectedRespuesta());
    }
}
