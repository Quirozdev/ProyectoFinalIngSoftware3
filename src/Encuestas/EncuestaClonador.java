package Encuestas;

import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

import java.util.ArrayList;

public class EncuestaClonador {
    public static Encuesta clonar(Encuesta encuesta) {
        Encuesta encuestaClonada = new Encuesta(encuesta.getNombre());
        for (PreguntaAbierta preguntaAbierta : encuesta.getPreguntasAbiertas()) {
            PreguntaAbierta preguntaAbiertaClonada = new PreguntaAbierta(preguntaAbierta.getEnunciado());
            encuestaClonada.agregarPregunta(preguntaAbiertaClonada);
        }

        for (PreguntaRespuestaUnica preguntaRespuestaUnica : encuesta.getPreguntasRespuestaUnica()) {
            PreguntaRespuestaUnica preguntaRespuestaUnicaClonada = new PreguntaRespuestaUnica(preguntaRespuestaUnica.getEnunciado(), new ArrayList<>(preguntaRespuestaUnica.getOpciones()));
            encuestaClonada.agregarPregunta(preguntaRespuestaUnicaClonada);
        }

        for (PreguntaRespuestaMultiple preguntaRespuestaMultiple : encuesta.getPreguntasRespuestaMultiple()) {
            PreguntaRespuestaMultiple preguntaRespuestaMultipleClonada = new PreguntaRespuestaMultiple(preguntaRespuestaMultiple.getEnunciado(), new ArrayList<>(preguntaRespuestaMultiple.getOpciones()));
            encuestaClonada.agregarPregunta(preguntaRespuestaMultipleClonada);
        }
        return encuestaClonada;
    }
}
