package app.Usuarios;

import java.io.Serializable;

public class Cajero extends Empleado implements Serializable {
    public Cajero(int id, String nombre, String apellido, String password) {
        super( id,nombre, apellido, password);
    }
    @Override
    public String toString() {
        return super.toString()+ " " + " puesto: Cajero";
    }
}
