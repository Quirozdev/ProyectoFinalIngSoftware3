package Base;

public abstract class PreguntaBase<T, V> implements Respondible<T, V> {
    private String enunciado;
    public PreguntaBase(String enunciado) {
        if (enunciado == null || enunciado.isEmpty()) {
            throw new IllegalArgumentException("La pregunta debe tener un enunciado");
        }

        this.enunciado = enunciado;
    }
    public String getEnunciado() {
        return this.enunciado;
    }

    public String toString() {
        return this.getEnunciado();
    }
}
