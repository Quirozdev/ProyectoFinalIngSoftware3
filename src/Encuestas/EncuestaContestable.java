package Encuestas;

import Encuestas.Encuesta;
import Encuestas.EncuestaClonador;
import Encuestas.IEncuesta;
import Encuestas.IEncuestaContestable;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

import java.util.List;

public class EncuestaContestable implements IEncuesta, IEncuestaContestable {
    private Encuesta encuesta;
    public EncuestaContestable(Encuesta encuesta) {
        this.encuesta = EncuestaClonador.clonar(encuesta);
    }

    public Encuesta getEncuesta() {
        return this.encuesta;
    }

    @Override
    public void contestarPreguntaAbierta(int indicePregunta, String respuesta) {
        this.encuesta.getPreguntasAbiertas().get(indicePregunta).setSelectedRespuesta(respuesta);
    }

    @Override
    public void contestarPreguntaRespuestaUnica(int indicePregunta, int indiceOpcion) {
        this.encuesta.getPreguntasRespuestaUnica().get(indicePregunta).setSelectedRespuesta(indiceOpcion);
    }

    @Override
    public void contestarPreguntaRespuestaMultiple(int indicePregunta, List<Integer> indicesOpciones) {
        this.encuesta.getPreguntasRespuestaMultiple().get(indicePregunta).setSelectedRespuesta(indicesOpciones);
    }

    @Override
    public String getNombre() {
        return this.encuesta.getNombre();
    }

    @Override
    public List<PreguntaAbierta> getPreguntasAbiertas() {
        return this.encuesta.getPreguntasAbiertas();
    }

    @Override
    public List<PreguntaRespuestaUnica> getPreguntasRespuestaUnica() {
        return this.encuesta.getPreguntasRespuestaUnica();
    }

    @Override
    public List<PreguntaRespuestaMultiple> getPreguntasRespuestaMultiple() {
        return this.encuesta.getPreguntasRespuestaMultiple();
    }
}
