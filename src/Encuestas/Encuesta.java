package Encuestas;

import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

import java.util.ArrayList;
import java.util.List;

public class Encuesta implements IEncuesta {
    private String nombre;
    private List<PreguntaRespuestaUnica> preguntasRespuestaUnica;
    private List<PreguntaRespuestaMultiple> preguntasRespuestaMultiple;
    private List<PreguntaAbierta> preguntasAbiertas;
    public Encuesta(String nombre) {
        this.nombre = nombre;
        this.preguntasRespuestaUnica = new ArrayList<>();
        this.preguntasRespuestaMultiple = new ArrayList<>();
        this.preguntasAbiertas = new ArrayList<>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void agregarPregunta(PreguntaAbierta pregunta) {
        this.preguntasAbiertas.add(pregunta);
    }

    public void agregarPregunta(PreguntaRespuestaUnica pregunta) {
        this.preguntasRespuestaUnica.add(pregunta);
    }

    public void agregarPregunta(PreguntaRespuestaMultiple pregunta) {
        this.preguntasRespuestaMultiple.add(pregunta);
    }

    public List<PreguntaAbierta> getPreguntasAbiertas() {
        return this.preguntasAbiertas;
    }

    public List<PreguntaRespuestaUnica> getPreguntasRespuestaUnica() {
        return this.preguntasRespuestaUnica;
    }

    public List<PreguntaRespuestaMultiple> getPreguntasRespuestaMultiple() {
        return this.preguntasRespuestaMultiple;
    }

    @Override public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Encuesta)) return false;
        Encuesta encuestaAComparar = (Encuesta) o;
        if (!encuestaAComparar.getNombre().equals(this.getNombre())) return false;
        if (encuestaAComparar.getPreguntasAbiertas().size() != this.getPreguntasAbiertas().size()) return false;
        for (int i = 0; i < encuestaAComparar.getPreguntasAbiertas().size(); i++) {
            if (!encuestaAComparar.getPreguntasAbiertas().get(i).equals(this.getPreguntasAbiertas().get(i))) return false;
        }
        if (encuestaAComparar.getPreguntasRespuestaUnica().size() != this.getPreguntasRespuestaUnica().size()) return false;
        for (int i = 0; i < encuestaAComparar.getPreguntasRespuestaUnica().size(); i++) {
            if (!encuestaAComparar.getPreguntasRespuestaUnica().get(i).equals(this.getPreguntasRespuestaUnica().get(i))) return false;
        }
        if (encuestaAComparar.getPreguntasRespuestaMultiple().size() != this.getPreguntasRespuestaMultiple().size()) return false;
        for (int i = 0; i < encuestaAComparar.getPreguntasRespuestaMultiple().size(); i++) {
            if (!encuestaAComparar.getPreguntasRespuestaMultiple().get(i).equals(this.getPreguntasRespuestaMultiple().get(i))) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.nombre);
        stringBuilder.append("\n");
        for (PreguntaRespuestaUnica preguntaRespuestaUnica : this.getPreguntasRespuestaUnica()) {
            stringBuilder.append(preguntaRespuestaUnica.toString());
            stringBuilder.append("\n");
        }
        for (PreguntaRespuestaMultiple preguntaRespuestaMultiple : this.getPreguntasRespuestaMultiple()) {
            stringBuilder.append(preguntaRespuestaMultiple.toString());
            stringBuilder.append("\n");
        }
        for (PreguntaAbierta preguntaAbierta : this.getPreguntasAbiertas()) {
            stringBuilder.append(preguntaAbierta.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
