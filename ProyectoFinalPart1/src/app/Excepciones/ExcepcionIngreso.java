package app.Excepciones;

public class ExcepcionIngreso extends Exception{
    private String mensaje;

    public ExcepcionIngreso( String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return mensaje;
    }
}
