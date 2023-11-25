package test;

import Consumers.Informe;
import Encuestas.Encuesta;
import Encuestas.EncuestaClonador;
import Encuestas.EncuestaContestable;
import Encuestas.EncuestaContestada;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import org.junit.jupiter.api.*;

import java.util.List;

public class InformeTest {

    private EncuestaContestada encuestaContestada;
    private EncuestaContestada encuestaContestadaDistintaEstructura;

    @BeforeEach
    public void setUp() {
        Encuesta encuesta = new Encuesta("Encuesta de satisfaccion");
        encuesta.agregarPregunta(new PreguntaAbierta("¿Cual es tu nombre?"));
        encuesta.agregarPregunta(new PreguntaRespuestaUnica("¿Cual es tu color favorito?", List.of("Rojo", "Azul", "Verde")));

        EncuestaContestable encuestaContestable = new EncuestaContestable(encuesta);
        encuestaContestable.contestarPreguntaAbierta(0, "Juan");
        encuestaContestable.contestarPreguntaRespuestaUnica(0, 1);

        encuestaContestada = new EncuestaContestada(encuestaContestable);

        Encuesta encuestaDistintaEstructura = new Encuesta("Encuesta de valoracion");
        encuestaDistintaEstructura.agregarPregunta(new PreguntaAbierta("Describe tu experiencia"));
        encuestaDistintaEstructura.agregarPregunta(new PreguntaRespuestaMultiple("¿Cuales son tus pasatiempos?", List.of("Futbol", "Basket", "Tenis")));

        EncuestaContestable encuestaDiferenteEstructuraContestable = new EncuestaContestable(encuestaDistintaEstructura);
        encuestaDiferenteEstructuraContestable.contestarPreguntaAbierta(0, "Muy buena");
        encuestaDiferenteEstructuraContestable.contestarPreguntaRespuestaMultiple(0, List.of(1, 2));

        encuestaContestadaDistintaEstructura = new EncuestaContestada(encuestaDiferenteEstructuraContestable);
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

        EncuestaContestada encuestaContestadaClonada = new EncuestaContestada(encuestaContestableClonada);

        List<EncuestaContestada> encuestasContestadas = List.of(encuestaContestada, encuestaContestadaClonada);

        Assertions.assertDoesNotThrow(() -> Informe.generar(encuestasContestadas));
    }

    @Test
    public void test03InformeConEncuestasContestadasMismaEstructuraCombinaRespuestasCorrectamente() {
        Encuesta encuestaClonada = EncuestaClonador.clonar(encuestaContestada.getEncuesta());

        EncuestaContestable encuestaContestableClonada = new EncuestaContestable(encuestaClonada);

        encuestaContestableClonada.contestarPreguntaAbierta(0, "Pancho");
        encuestaContestableClonada.contestarPreguntaRespuestaUnica(0, 2);

        EncuestaContestada encuestaContestadaClonada = new EncuestaContestada(encuestaContestableClonada);

        List<EncuestaContestada> encuestasContestadas = List.of(encuestaContestada, encuestaContestadaClonada);

        Informe.MergedRespuestas mergedRespuestas = Informe.mergeRespuestas(encuestasContestadas);

        Assertions.assertTrue(mergedRespuestas.getPreguntasAbiertasConSusRespuestas().containsValue(List.of("Juan", "Pancho")));
        Assertions.assertTrue(mergedRespuestas.getPreguntasRespuestaUnicaConSusRespuestas().containsValue(List.of(1, 2)));
        Assertions.assertTrue(mergedRespuestas.getPreguntasRespuestaMultipleConSusRespuestas().values().isEmpty());
    }

}
