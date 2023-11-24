package Strategies;

import Base.PreguntaCerradaBase;

import java.util.List;

public class PreguntaRespuestaUnica extends PreguntaCerradaBase<Integer, Integer> {
    private Integer indiceOpcionSeleccionada;

    public PreguntaRespuestaUnica(String enunciado, List<String> opciones) {
        super(enunciado, opciones);
    }

    @Override
    public Integer getSelectedRespuesta() {
        return this.indiceOpcionSeleccionada;
    }

    @Override
    public void setSelectedRespuesta(Integer indiceOpcion) {
        if (indiceOpcion <= 0 || indiceOpcion > this.getOpciones().size()) {
            throw new IllegalArgumentException("El indice de la opcion seleccionada no es valido");
        }
        this.indiceOpcionSeleccionada = indiceOpcion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PreguntaRespuestaUnica)) return false;
        PreguntaRespuestaUnica preguntaAComparar = (PreguntaRespuestaUnica) o;
        if (!preguntaAComparar.getEnunciado().equals(this.getEnunciado())) return false;
        if (preguntaAComparar.getOpciones().size() != this.getOpciones().size()) return false;
        for (int i = 0; i < preguntaAComparar.getOpciones().size(); i++) {
            if (!preguntaAComparar.getOpciones().get(i).equals(this.getOpciones().get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.getEnunciado().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getEnunciado());
        stringBuilder.append("\n");
        for (int i = 0; i < this.getOpciones().size(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(this.getOpciones().get(i));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
