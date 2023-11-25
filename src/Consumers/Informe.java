package Consumers;

import Encuestas.EncuestaContestada;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Informe {
    private Informe() {
    }

    public static void generar(List<EncuestaContestada> encuestasContestadas) {
        if (isEncuestasContestadasEmpty(encuestasContestadas)) {
            throw new IllegalArgumentException("No se puede crear un informe sin encuestas contestadas");
        }
        if (!isEncuestasContestadasNotTheSameType(encuestasContestadas)) {
            throw new IllegalArgumentException("No se puede crear un informe con encuestas distintas");
        }

        MergedRespuestas mergedRespuestas = Informe.mergeRespuestas(encuestasContestadas);

        System.out.println("Informe: " + encuestasContestadas.get(0).getEncuesta().getNombre() + "\n");
        System.out.println(mergedRespuestas);
    }

    private static boolean isEncuestasContestadasEmpty(List<EncuestaContestada> encuestasContestadas) {
        return encuestasContestadas == null || encuestasContestadas.isEmpty();
    }

    private static boolean isEncuestasContestadasNotTheSameType(List<EncuestaContestada> encuestasContestadas) {
        // checar que todas las encuestas tengan el mismo formato/mismas preguntas
        for (EncuestaContestada encuestaContestada : encuestasContestadas) {
            if (!encuestaContestada.getEncuesta().equals(encuestasContestadas.get(0).getEncuesta())) {
                return false;
            }
        }
        return true;
    }

    public static MergedRespuestas mergeRespuestas(List<EncuestaContestada> encuestasContestadas) {
        HashMap<PreguntaAbierta, List<String>> preguntasAbiertasConSusRespuestas = new HashMap<>();
        HashMap<PreguntaRespuestaUnica, List<Integer>> preguntasRespuestaUnicaConSusRespuestas = new HashMap<>();
        HashMap<PreguntaRespuestaMultiple, List<List<Integer>>> preguntasRespuestaMultipleConSusRespuestas = new HashMap<>();

        for (EncuestaContestada encuestaContestada : encuestasContestadas) {
            for (PreguntaAbierta preguntaAbierta : encuestaContestada.getEncuesta().getPreguntasAbiertas()) {
                if (preguntasAbiertasConSusRespuestas.containsKey(preguntaAbierta)) {
                    preguntasAbiertasConSusRespuestas.get(preguntaAbierta).add(preguntaAbierta.getSelectedRespuesta());
                } else {
                    preguntasAbiertasConSusRespuestas.put(preguntaAbierta, new ArrayList<>(Arrays.asList(preguntaAbierta.getSelectedRespuesta())));
                }
            }

            for (PreguntaRespuestaUnica preguntaRespuestaUnica : encuestaContestada.getEncuesta().getPreguntasRespuestaUnica()) {
                if (preguntasRespuestaUnicaConSusRespuestas.containsKey(preguntaRespuestaUnica)) {
                    preguntasRespuestaUnicaConSusRespuestas.get(preguntaRespuestaUnica).add(preguntaRespuestaUnica.getSelectedRespuesta());
                } else {
                    preguntasRespuestaUnicaConSusRespuestas.put(preguntaRespuestaUnica, new ArrayList<>(Arrays.asList(preguntaRespuestaUnica.getSelectedRespuesta())));
                }
            }

            for (PreguntaRespuestaMultiple preguntaRespuestaMultiple : encuestaContestada.getEncuesta().getPreguntasRespuestaMultiple()) {
                if (preguntasRespuestaMultipleConSusRespuestas.containsKey(preguntaRespuestaMultiple)) {
                    preguntasRespuestaMultipleConSusRespuestas.get(preguntaRespuestaMultiple).add(preguntaRespuestaMultiple.getSelectedRespuesta());
                } else {
                    preguntasRespuestaMultipleConSusRespuestas.put(preguntaRespuestaMultiple, new ArrayList<>(Arrays.asList(preguntaRespuestaMultiple.getSelectedRespuesta())));
                }
            }
        }
        return new MergedRespuestas(preguntasAbiertasConSusRespuestas, preguntasRespuestaUnicaConSusRespuestas, preguntasRespuestaMultipleConSusRespuestas);
    }

    public static class MergedRespuestas {
        private HashMap<PreguntaAbierta, List<String>> preguntasAbiertasConSusRespuestas;
        private HashMap<PreguntaRespuestaUnica, List<Integer>> preguntasRespuestaUnicaConSusRespuestas;
        private HashMap<PreguntaRespuestaMultiple, List<List<Integer>>> preguntasRespuestaMultipleConSusRespuestas;

        public MergedRespuestas(HashMap<PreguntaAbierta, List<String>> preguntasAbiertasConSusRespuestas, HashMap<PreguntaRespuestaUnica, List<Integer>> preguntasRespuestaUnicaConSusRespuestas, HashMap<PreguntaRespuestaMultiple, List<List<Integer>>> preguntasRespuestaMultipleConSusRespuestas) {
            this.preguntasAbiertasConSusRespuestas = preguntasAbiertasConSusRespuestas;
            this.preguntasRespuestaUnicaConSusRespuestas = preguntasRespuestaUnicaConSusRespuestas;
            this.preguntasRespuestaMultipleConSusRespuestas = preguntasRespuestaMultipleConSusRespuestas;
        }

        public HashMap<PreguntaAbierta, List<String>> getPreguntasAbiertasConSusRespuestas() {
            return this.preguntasAbiertasConSusRespuestas;
        }

        public HashMap<PreguntaRespuestaUnica, List<Integer>> getPreguntasRespuestaUnicaConSusRespuestas() {
            return this.preguntasRespuestaUnicaConSusRespuestas;
        }

        public HashMap<PreguntaRespuestaMultiple, List<List<Integer>>> getPreguntasRespuestaMultipleConSusRespuestas() {
            return this.preguntasRespuestaMultipleConSusRespuestas;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (PreguntaRespuestaUnica preguntaRespuestaUnica : this.preguntasRespuestaUnicaConSusRespuestas.keySet()) {
                stringBuilder.append(preguntaRespuestaUnica.toString());
                stringBuilder.append("\nRespuestas:\n");
                for (Integer respuesta : this.preguntasRespuestaUnicaConSusRespuestas.get(preguntaRespuestaUnica)) {
                    stringBuilder.append(respuesta);
                    stringBuilder.append("\n");
                }
                stringBuilder.append("--------------------------------------------------------------\n");
            }

            for (PreguntaRespuestaMultiple preguntaRespuestaMultiple : this.preguntasRespuestaMultipleConSusRespuestas.keySet()) {
                stringBuilder.append(preguntaRespuestaMultiple.toString());
                stringBuilder.append("\nRespuestas:\n");
                for (List<Integer> respuesta : this.preguntasRespuestaMultipleConSusRespuestas.get(preguntaRespuestaMultiple)) {
                    stringBuilder.append(respuesta.toString());
                    stringBuilder.append("\n");
                }
                stringBuilder.append("--------------------------------------------------------------\n");
            }

            for (PreguntaAbierta preguntaAbierta : this.preguntasAbiertasConSusRespuestas.keySet()) {
                stringBuilder.append(preguntaAbierta.toString());
                stringBuilder.append("\nRespuestas:\n");
                for (String respuesta : this.preguntasAbiertasConSusRespuestas.get(preguntaAbierta)) {
                    stringBuilder.append(respuesta);
                    stringBuilder.append("\n");
                }
                stringBuilder.append("--------------------------------------------------------------\n");
            }
            return stringBuilder.toString();
        }
    }
}
