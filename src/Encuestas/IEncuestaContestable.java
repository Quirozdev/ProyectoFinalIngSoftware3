package Encuestas;

import java.util.List;

public interface IEncuestaContestable {
    public void contestarPreguntaAbierta(int indicePregunta, String respuesta);

    public void contestarPreguntaRespuestaUnica(int indicePregunta, int indiceOpcion);

    public void contestarPreguntaRespuestaMultiple(int indicePregunta, List<Integer> indicesOpciones);

    public boolean preguntasRespuestaUnicaHanSidoContestadas();

    public boolean preguntasRespuestaMultipleHanSidoContestadas();

    public boolean preguntasAbiertasHanSidoContestadas();

    public boolean haSidoContestadaCompletamente();
}
