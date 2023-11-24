package Builders;

import Strategies.PreguntaAbierta;

import java.util.List;

public class PreguntaBuilder implements IPreguntaBuilder {
    private String enunciado;
    public PreguntaBuilder(String enunciado) {
        this.enunciado = enunciado;
    }

    @Override
    public PreguntaAbierta siendoPreguntaAbierta() {
        return new PreguntaAbierta(this.enunciado);
    }

    @Override
    public PreguntaCerradaBuilder siendoPreguntaCerrada(List<String> opciones) {
        return new PreguntaCerradaBuilder(this.enunciado, opciones);
    }
}
