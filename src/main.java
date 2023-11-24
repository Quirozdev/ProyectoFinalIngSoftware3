import Consumers.EncuestaContestador;
import Consumers.EncuestaCreador;
import Encuestas.Encuesta;
import Factories.PreguntaFactory;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        Encuesta encuesta = EncuestaCreador.crearEncuesta();

        EncuestaContestador.contestarEncuesta(encuesta);


//        Encuesta encuesta = new Encuesta("Encuesta random");
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaUnica("que color prefieres?", new ArrayList<>() {
//            {
//                add("Rojo");
//                add("Azul");
//                add("Verde");
//            }
//        }));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaUnica("que tanto te agrada el servicio?", new ArrayList<>() {
//            {
//                add("Muy poco");
//                add("Poco");
//                add("Neutral");
//                add("Mucho");
//            }
//        }));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaUnica("Estas de acuerdo en que el servicio es bueno?", new ArrayList<>() {
//            {
//                add("Si");
//                add("No");
//            }
//        }));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaUnica("¿Que materia quitarias del plan de la carrera?", new ArrayList<>() {
//            {
//                add("Quimica");
//                add("Fisica");
//                add("Caracteristicas de la sociedad actual");
//                add("Ninguna");
//            }
//        }));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaMultiple("Selecciona lo que haces en tus tiempos libres", new ArrayList<>() {
//            {
//                add("Leer");
//                add("Ver peliculas");
//                add("Salir con amigos");
//                add("Jugar videojuegos");
//                add("Otro");
//            }
//        }));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaMultiple("que tipo de peliculas te gustan?", new ArrayList<>() {
//            {
//                add("Terror");
//                add("Comedia");
//                add("Drama");
//                add("Accion");
//                add("Otro");
//            }
//        }));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaRespuestaMultiple("que tipo de musica escuchas?", new ArrayList<>() {
//            {
//                add("Rock");
//                add("Pop");
//                add("Rap");
//                add("Otro");
//            }
//        }));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaAbierta("Cual es tu opinion sobre mexico?"));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaAbierta("Ofrece una breve descripcion de tu universidad"));
//
//        encuesta.agregarPregunta(PreguntaFactory.crearPreguntaAbierta("Escribe una breve descripcion sobre ti"));
    }

}

