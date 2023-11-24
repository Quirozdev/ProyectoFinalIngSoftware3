package Encuestas;

import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

import java.util.List;

public interface IEncuesta {
    public String getNombre();

    public List<PreguntaAbierta> getPreguntasAbiertas();

    public List<PreguntaRespuestaUnica> getPreguntasRespuestaUnica();
    public List<PreguntaRespuestaMultiple> getPreguntasRespuestaMultiple();
}
