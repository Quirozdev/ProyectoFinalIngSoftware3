package Base;

import java.util.List;

public abstract class PreguntaCerradaBase<T, V> extends PreguntaBase<T, V> {
    private List<String> opciones;

    public PreguntaCerradaBase(String enunciado, List<String> opciones) {
        super(enunciado);
        if (opciones == null ||opciones.isEmpty()) {
            throw new IllegalArgumentException("La pregunta debe tener al menos 1 opcion");
        }
        this.opciones = opciones;
    }
    public List<String> getOpciones() {
        return this.opciones;
    }
}
