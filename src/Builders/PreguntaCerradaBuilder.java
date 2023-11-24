package Builders;

import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

import java.util.List;

public class PreguntaCerradaBuilder {
    private String enunciado;
    private List<String> opciones;
    public PreguntaCerradaBuilder(String enunciado, List<String> opciones) {
        this.enunciado = enunciado;
        this.opciones = opciones;
    }
    public PreguntaRespuestaUnica conRespuestaUnica() {
        return new PreguntaRespuestaUnica(this.enunciado, this.opciones);
    }

    public PreguntaRespuestaMultiple conRespuestaMultiple() {
        return new PreguntaRespuestaMultiple(this.enunciado, this.opciones);
    }
}
