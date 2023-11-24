package Consumers;

import Encuestas.Encuesta;
import Encuestas.EncuestaContestable;
import Encuestas.EncuestaContestada;
import Strategies.PreguntaAbierta;
import Strategies.PreguntaRespuestaMultiple;
import Strategies.PreguntaRespuestaUnica;
import Utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EncuestaContestador {
    private static List<EncuestaContestada> encuestasContestadas = new ArrayList<>();
    private EncuestaContestador() {}

    public static void contestarEncuesta(Encuesta encuesta) {
        Scanner scanner = new Scanner(System.in);

        EncuestaContestable encuestaContestable = new EncuestaContestable(encuesta);

        while (true) {
            Logger.logln("Bienvenid@ a la encuesta: " + encuesta.getNombre() + "\n");

            for (int i = 0; i < encuestaContestable.getPreguntasRespuestaUnica().size(); i++) {
                PreguntaRespuestaUnica pregunta = encuestaContestable.getPreguntasRespuestaUnica().get(i);
                Logger.logln("\n" + pregunta);
                Logger.log("Ingrese su respuesta: ");
                try {
                    int respuesta = scanner.nextInt();
                    encuestaContestable.contestarPreguntaRespuestaUnica(i, respuesta);
                    scanner.nextLine();
                } catch (Exception e) {
                    scanner.nextLine();
                    Logger.logError("\nIngrese una opcion valida en el rango: " + "[ 1 - " + pregunta.getOpciones().size() + " ]");
                    i--;
                }
            }

            for (int i = 0; i < encuestaContestable.getPreguntasRespuestaMultiple().size(); i++) {
                PreguntaRespuestaMultiple pregunta = encuestaContestable.getPreguntasRespuestaMultiple().get(i);
                Logger.logln("\n" + pregunta);
                Logger.log("Ingrese sus opciones separadas por comas: ");
                String opciones = scanner.nextLine();
                String[] opcionesSeparadas = opciones.split(",");
                ArrayList<Integer> opcionesElegidas = new ArrayList<>();
                try {
                    for (String respuesta : opcionesSeparadas) {
                        opcionesElegidas.add(Integer.parseInt(respuesta.trim()));
                    }
                    encuestaContestable.contestarPreguntaRespuestaMultiple(i, opcionesElegidas);
                } catch (Exception e) {
                    Logger.logError("Ingrese opciones validas en el rango: " + "[ 1 - " + pregunta.getOpciones().size() + " ]");
                    i--;
                }
            }

            for (int i = 0; i < encuestaContestable.getPreguntasAbiertas().size(); i++) {
                PreguntaAbierta pregunta = encuestaContestable.getPreguntasAbiertas().get(i);
                Logger.logln("\n" + pregunta);
                Logger.log("Ingrese su respuesta: ");
                String respuesta = scanner.nextLine();
                try {
                    encuestaContestable.contestarPreguntaAbierta(i, respuesta);
                } catch (Exception e) {
                    Logger.logError("Ingrese una respuesta no vacia valida");
                    i--;
                }
            }

            Logger.logSuccess("Gracias por contestar la encuesta!" + "\n");
            EncuestaContestada encuestaContestada = new EncuestaContestada(encuestaContestable);
            encuestasContestadas.add(encuestaContestada);

            while (true) {
                Logger.logln("1. Ver informe de las encuestas contestadas");
                Logger.logln("2. Contestar la encuesta nuevamente");
                Logger.logln("3. Salir");
                Logger.log("Ingrese su opcion: ");
                try {
                    int opcion = scanner.nextInt();
                    if (opcion == 1) {
                        Logger.log("\n");
                        Informe.generar(encuestasContestadas);
                    } else if (opcion == 2) {
                        contestarEncuesta(encuesta);
                    } else if (opcion == 3) {
                        System.exit(0);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    scanner.nextLine();
                    Logger.logError("Ingrese una opcion valida" + "\n");
                }
            }
        }
    }
}
