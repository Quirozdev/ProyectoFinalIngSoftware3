package Factories;

import Builders.PreguntaBuilder;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

import java.util.List;

public class PreguntaFactory {
    public static PreguntaAbierta crearPreguntaAbierta(String enunciado) {
        return new PreguntaBuilder(enunciado).siendoPreguntaAbierta();
    }

    public static PreguntaRespuestaUnica crearPreguntaRespuestaUnica(String enunciado, List<String> opciones) {
        return new PreguntaBuilder(enunciado).siendoPreguntaCerrada(opciones).conRespuestaUnica();
    }

    public static PreguntaRespuestaMultiple crearPreguntaRespuestaMultiple(String enunciado, List<String> opciones) {
        return new PreguntaBuilder(enunciado).siendoPreguntaCerrada(opciones).conRespuestaMultiple();
    }
}
