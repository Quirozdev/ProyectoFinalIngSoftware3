package Consumers;

import Encuestas.Encuesta;
import Factories.PreguntaFactory;
import Utils.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class EncuestaCreador {
    private EncuestaCreador() {
    }

    public static Encuesta crearEncuesta() {
        Scanner scanner = new Scanner(System.in);

        Logger.logln("Bienvenid@ a la creacion de encuestas!");
        String nombreEncuesta;
        while (true) {
            Logger.log("Ingrese el nombre de la encuesta: ");
            nombreEncuesta = scanner.nextLine();
            if (nombreEncuesta.isEmpty()) {
                Logger.logError("Ingrese un nombre valido");
            } else {
                break;
            }
        }

        Encuesta encuesta = new Encuesta(nombreEncuesta);

        while (true) {
            Logger.logln("¿Qué desea hacer con la encuesta?: ");
            Logger.logln("1. Agregar pregunta cerrada de respuesta unica");
            Logger.logln("2. Agregar pregunta cerrada de respuesta multiple");
            Logger.logln("3. Agregar pregunta de respuesta abierta");
            Logger.logln("4. Ver estructura de la encuesta");
            Logger.logln("5. Terminar de agregar preguntas");
            Logger.log("Ingrese su opcion: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion < 1 || opcion > 5) throw new Exception();

                if (opcion == 5) {
                    Logger.logSuccess("Encuesta creada con exito!");
                    return encuesta;
                }

                if (opcion == 4) {
                    Logger.logln("Estructura de la encuesta: ");
                    Logger.logln(encuesta.toString());
                    continue;
                }

                String enunciado;

                while (true) {
                    Logger.logln("Ingrese el enunciado de la pregunta: ");
                    enunciado = scanner.nextLine();
                    if (enunciado.isEmpty()) {
                        Logger.logError("Ingrese un enunciado valido");
                    } else {
                        break;
                    }
                }


                if (opcion == 1) {
                    while (true) {
                        Logger.logln("Ingrese las opciones separadas por comas: ");
                        try {
                            String linea = scanner.nextLine();
                            if (linea.isEmpty()) {
                                throw new Exception();
                            }
                            String[] opcionesSeparadas = linea.split(",");
                            ArrayList<String> opciones = new ArrayList<>();
                            for (String opcionSeparada : opcionesSeparadas) {
                                opciones.add(opcionSeparada.trim());
                            }
                            encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaUnica(enunciado, opciones));
                            break;
                        } catch (Exception e) {
                            Logger.logError("Ingrese opciones validas");
                        }
                    }
                }

                if (opcion == 2) {
                    while (true) {
                        Logger.logln("Ingrese las opciones separadas por comas: ");
                        try {
                            String linea = scanner.nextLine();
                            if (linea.isEmpty()) {
                                throw new Exception();
                            }
                            String[] opcionesSeparadas = linea.split(",");
                            ArrayList<String> opciones = new ArrayList<>();
                            for (String opcionSeparada : opcionesSeparadas) {
                                opciones.add(opcionSeparada.trim());
                            }
                            encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaMultiple(enunciado, opciones));
                            break;
                        } catch (Exception e) {
                            Logger.logError("Ingrese opciones validas");
                        }
                    }
                }

                if (opcion == 3) {
                    encuesta.agregarPregunta(PreguntaFactory.crearPreguntaAbierta(enunciado));
                }

            } catch (Exception e) {
//                scanner.nextLine();
                Logger.logError("Ingrese una opcion valida");
            }
        }
    }
}
