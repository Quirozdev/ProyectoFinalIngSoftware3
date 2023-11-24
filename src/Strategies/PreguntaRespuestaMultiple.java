package Strategies;

import Base.PreguntaCerradaBase;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class PreguntaRespuestaMultiple extends PreguntaCerradaBase<List<Integer>, List<Integer>> {
    private List<Integer> indicesOpcionesSeleccionadas;

    public PreguntaRespuestaMultiple(String enunciado, List<String> opciones) {
        super(enunciado, opciones);
    }

    @Override
    public List<Integer> getSelectedRespuesta() {
        return indicesOpcionesSeleccionadas;
    }

    @Override
    public void setSelectedRespuesta(List<Integer> indicesOpciones) {
        if (indicesOpciones == null || indicesOpciones.isEmpty()) {
            throw new IllegalArgumentException("La lista de indices de opciones seleccionadas no puede estar vacia");
        }
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(indicesOpciones);
        ArrayList<Integer> opcionesNoDuplicadas = new ArrayList<>(hashSet);
        for (Integer indiceOpcion : opcionesNoDuplicadas) {
            if (indiceOpcion <= 0 || indiceOpcion > this.getOpciones().size()) {
                throw new IllegalArgumentException("El indice de la opcion seleccionada no es valido");
            }
        }
        this.indicesOpcionesSeleccionadas = opcionesNoDuplicadas;
    }

    @Override
    public int hashCode() {
        return this.getEnunciado().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PreguntaRespuestaMultiple)) return false;
        PreguntaRespuestaMultiple preguntaAComparar = (PreguntaRespuestaMultiple) o;
        if (!preguntaAComparar.getEnunciado().equals(this.getEnunciado())) return false;
        if (preguntaAComparar.getOpciones().size() != this.getOpciones().size()) return false;
        for (int i = 0; i < preguntaAComparar.getOpciones().size(); i++) {
            if (!preguntaAComparar.getOpciones().get(i).equals(this.getOpciones().get(i))) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getEnunciado());
        stringBuilder.append("\n");
        for (int i = 0; i < this.getOpciones().size(); i++) {
            stringBuilder.append("[");
            stringBuilder.append(i + 1);
            stringBuilder.append("]");
            stringBuilder.append(this.getOpciones().get(i));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
