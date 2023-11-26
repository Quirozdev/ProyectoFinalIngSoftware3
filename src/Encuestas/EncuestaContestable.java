package Encuestas;

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
    public boolean preguntasRespuestaUnicaHanSidoContestadas() {
        for (PreguntaRespuestaUnica preguntaRespuestaUnica : this.getEncuesta().getPreguntasRespuestaUnica()) {
            if (preguntaRespuestaUnica.getSelectedRespuesta() == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean preguntasRespuestaMultipleHanSidoContestadas() {
        for (PreguntaRespuestaMultiple preguntaRespuestaMultiple : this.getEncuesta().getPreguntasRespuestaMultiple()) {
            if (preguntaRespuestaMultiple.getSelectedRespuesta() == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean preguntasAbiertasHanSidoContestadas() {
        for (PreguntaAbierta preguntaAbierta : this.getEncuesta().getPreguntasAbiertas()) {
            if (preguntaAbierta.getSelectedRespuesta() == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean haSidoContestadaCompletamente() {
        if (!this.preguntasRespuestaUnicaHanSidoContestadas()) {
            return false;
        }
        if (!this.preguntasRespuestaMultipleHanSidoContestadas()) {
            return false;
        }
        if (!this.preguntasAbiertasHanSidoContestadas()) {
            return false;
        }
        return true;
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
