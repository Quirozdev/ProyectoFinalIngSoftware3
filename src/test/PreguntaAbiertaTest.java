package test;

import Strategies.PreguntaAbierta;
import org.junit.jupiter.api.*;


public class PreguntaAbiertaTest {

    @Test
    public void test00PreguntaAbiertaNoSeCreaConEnunciadoVacio() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PreguntaAbierta(""));
    }

    @Test
    public void test01PreguntaAbiertaSeCreaConEnunciado() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cual es tu nombre?");
        Assertions.assertEquals("¿Cual es tu nombre?", preguntaAbierta.getEnunciado());
    }

    @Test
    public void test02PreguntaAbiertaSeContestaCorrectamente() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cual es tu nombre?");
        preguntaAbierta.setSelectedRespuesta("Juan");
        Assertions.assertEquals("Juan", preguntaAbierta.getSelectedRespuesta());
    }

    @Test
    public void test03PreguntaAbiertaNoSePuedeContestarConRespuestaVacia() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cual es tu nombre?");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> preguntaAbierta.setSelectedRespuesta(""));
    }

    @Test
    public void test04PreguntaAbiertaNoSePuedeContestarConRespuestaNula() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cual es tu nombre?");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> preguntaAbierta.setSelectedRespuesta(null));
    }

    @Test
    public void test05PreguntaAbiertasSonIgualesSiTienenMismoEnunciado() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cual es tu nombre?");
        PreguntaAbierta otraPreguntaAbierta = new PreguntaAbierta("¿Cual es tu nombre?");
        Assertions.assertEquals(preguntaAbierta, otraPreguntaAbierta);
    }

    @Test
    public void test06PreguntaAbiertasSonDistintasSiTienenDistintoEnunciado() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cual es tu nombre?");
        PreguntaAbierta otraPreguntaAbierta = new PreguntaAbierta("¿Cual es tu apellido?");
        Assertions.assertNotEquals(preguntaAbierta, otraPreguntaAbierta);
    }
}