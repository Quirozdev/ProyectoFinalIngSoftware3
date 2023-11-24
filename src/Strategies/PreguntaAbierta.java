package Strategies;

import Base.PreguntaBase;

public class PreguntaAbierta extends PreguntaBase<String, String> {
    private String respuesta;

    public PreguntaAbierta(String enunciado) {
        super(enunciado);
    }

    @Override
    public String getSelectedRespuesta() {
        return this.respuesta;
    }
    @Override
    public void setSelectedRespuesta(String respuesta) {
        if (respuesta == null || respuesta.isEmpty()) {
            throw new IllegalArgumentException("La respuesta no puede estar vacia");
        }
        this.respuesta = respuesta;
    }

    @Override
    public int hashCode() {
        return this.getEnunciado().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PreguntaAbierta)) return false;
        PreguntaAbierta preguntaAComparar = (PreguntaAbierta) o;
        if (!preguntaAComparar.getEnunciado().equals(this.getEnunciado())) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getEnunciado());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
