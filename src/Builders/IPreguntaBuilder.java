package Builders;

import Strategies.PreguntaAbierta;

import java.util.List;

public interface IPreguntaBuilder {
    public PreguntaAbierta siendoPreguntaAbierta();

    public PreguntaCerradaBuilder siendoPreguntaCerrada(List<String> opciones);
}
