package Encuestas;

import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

public class EncuestaContestada {
    EncuestaContestable encuestaContestada;

    public EncuestaContestada(EncuestaContestable encuestaContestada) {
        for (PreguntaRespuestaUnica preguntaRespuestaUnica : encuestaContestada.getEncuesta().getPreguntasRespuestaUnica()) {
            if (preguntaRespuestaUnica.getSelectedRespuesta() == null) {
                throw new IllegalArgumentException("No se ha contestado la pregunta: " + preguntaRespuestaUnica.getEnunciado());
            }
        }
        for (PreguntaRespuestaMultiple preguntaRespuestaMultiple : encuestaContestada.getEncuesta().getPreguntasRespuestaMultiple()) {
            if (preguntaRespuestaMultiple.getSelectedRespuesta() == null) {
                throw new IllegalArgumentException("No se ha contestado la pregunta: " + preguntaRespuestaMultiple.getEnunciado());
            }
        }
        for (PreguntaAbierta preguntaAbierta : encuestaContestada.getEncuesta().getPreguntasAbiertas()) {
            if (preguntaAbierta.getSelectedRespuesta() == null) {
                throw new IllegalArgumentException("No se ha contestado la pregunta: " + preguntaAbierta.getEnunciado());
            }
        }
        this.encuestaContestada = encuestaContestada;
    }

    public Encuesta getEncuesta() {
        return this.encuestaContestada.getEncuesta();
    }

    public void mostrarResultados() {
        System.out.println(this.encuestaContestada.getEncuesta().getNombre());
        for (PreguntaRespuestaUnica preguntaRespuestaUnica : this.encuestaContestada.getEncuesta().getPreguntasRespuestaUnica()) {
            System.out.println(preguntaRespuestaUnica);
            System.out.println("Respuesta: " + preguntaRespuestaUnica.getSelectedRespuesta());
            System.out.println();
        }
        for (PreguntaRespuestaMultiple preguntaRespuestaMultiple : this.encuestaContestada.getEncuesta().getPreguntasRespuestaMultiple()) {
            System.out.println(preguntaRespuestaMultiple);
            System.out.println("Respuesta: " + preguntaRespuestaMultiple.getSelectedRespuesta());
            System.out.println();
        }
        for (PreguntaAbierta preguntaAbierta : this.encuestaContestada.getEncuesta().getPreguntasAbiertas()) {
            System.out.println(preguntaAbierta);
            System.out.println("Respuesta: " + preguntaAbierta.getSelectedRespuesta());
            System.out.println();
        }
    }
}
