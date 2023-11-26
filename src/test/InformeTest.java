package test;

import Consumers.Informe;
import Encuestas.Encuesta;
import Encuestas.EncuestaClonador;
import Encuestas.EncuestaContestable;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.*;

import java.util.List;

public class InformeTest {

    private EncuestaContestable encuestaContestada;
    private EncuestaContestable encuestaContestadaDistintaEstructura;

    @BeforeEach
    public void setUp() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));

        encuestaContestada = new EncuestaContestable(encuesta);
        encuestaContestada.contestarPreguntaAbierta(0, "Juan");
        encuestaContestada.contestarPreguntaRespuestaUnica(0, 1);

        Encuesta encuestaDistintaEstructura = new Encuesta("Encuesta de valoracion");
        encuestaDistintaEstructura.agregarPregunta(new PreguntaAbierta("Describe tu experiencia"));
        encuestaDistintaEstructura.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus pasatiempos?", List.of("Futbol", "Basket", "Tenis")));

        encuestaContestadaDistintaEstructura = new EncuestaContestable(encuestaDistintaEstructura);
        encuestaContestadaDistintaEstructura.contestarPreguntaAbierta(0, "Muy buena");
        encuestaContestadaDistintaEstructura.contestarPreguntaRespuestaMultiple(0, List.of(1, 2));
    }
    @Test
    public void test00InformeEncuestasContestadasVaciasONulas() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Informe.generar(List.of()));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Informe.generar(null));
    }

    @Test
    public void test01InformeConEncuestasDiferenteEstructura() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Informe.generar(List.of(encuestaContestada, encuestaContestadaDistintaEstructura)));
    }

    @Test
    public void test02InformeConEncuestasContestadasMismaEstructura() {
        Encuesta encuestaClonada = EncuestaClonador.clonar(encuestaContestada.getEncuesta());

        EncuestaContestable encuestaContestableClonada = new EncuestaContestable(encuestaClonada);

        encuestaContestableClonada.contestarPreguntaAbierta(0, "Pancho");
        encuestaContestableClonada.contestarPreguntaRespuestaUnica(0, 2);

        List<EncuestaContestable> encuestasContestadas = List.of(encuestaContestada, encuestaContestableClonada);

        Assertions.assertDoesNotThrow(() -> Informe.generar(encuestasContestadas));
    }

    @Test
    public void test03InformeConEncuestasContestadasMismaEstructuraCombinaRespuestasCorrectamente() {
        Encuesta encuestaClonada = EncuestaClonador.clonar(encuestaContestada.getEncuesta());

        EncuestaContestable encuestaContestableClonada = new EncuestaContestable(encuestaClonada);

        encuestaContestableClonada.contestarPreguntaAbierta(0, "Pancho");
        encuestaContestableClonada.contestarPreguntaRespuestaUnica(0, 2);

        List<EncuestaContestable> encuestasContestadas = List.of(encuestaContestada, encuestaContestableClonada);

        Informe.MergedRespuestas mergedRespuestas = Informe.mergeRespuestas(encuestasContestadas);

        Assertions.assertTrue(mergedRespuestas.getPreguntasAbiertasConSusRespuestas().containsValue(List.of("Juan", "Pancho")));
        Assertions.assertTrue(mergedRespuestas.getPreguntasRespuestaUnicaConSusRespuestas().containsValue(List.of(1, 2)));
        Assertions.assertTrue(mergedRespuestas.getPreguntasRespuestaMultipleConSusRespuestas().values().isEmpty());
    }

    @Test
    public void test04InformeConEncuestasNoContestadasCompletamente() {
        Encuesta encuestaClonada = EncuestaClonador.clonar(encuestaContestada.getEncuesta());

        EncuestaContestable encuestaContestableClonada = new EncuestaContestable(encuestaClonada);

        encuestaContestableClonada.contestarPreguntaAbierta(0, "Pancho");

        List<EncuestaContestable> encuestasContestadas = List.of(encuestaContestada, encuestaContestableClonada);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Informe.generar(encuestasContestadas));
    }

}
