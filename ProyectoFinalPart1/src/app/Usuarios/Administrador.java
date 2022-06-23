package app.Usuarios;

import app.Contenedores.ContenedorEmpleados;

import java.io.Serializable;

public class Administrador extends Empleado implements Serializable {

    private String log;

    public Administrador(int id,String nombre, String apellido, String password,String log) {
        super(id,nombre, apellido, password);
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    // dar de baja
    public boolean darDeBaja(int id, ContenedorEmpleados contenedor)
    {
        boolean rta= false;
        Empleado us=contenedor.buscar(id,getLog());
        if(us!= null)
        {
            us.darDeBaja();
            rta= true;
        }
        return rta;
    }

    public boolean darDeBaja(String nombre, String apellido, ContenedorEmpleados contenedor)
    {
        boolean rta= false;
        Empleado us=contenedor.buscar(nombre,apellido);
        if(us!= null)
        {
            us.darDeBaja();
            rta= true;
        }
        return rta;
    }
    // dar de alta
    public boolean darDeAlta(int id, ContenedorEmpleados contenedor)
    {
        boolean rta= false;
        Empleado us=contenedor.buscar(id,getLog());
        if(us!= null)
        {
            us.darDeAlta();
            rta= true;
        }
        return rta;
    }

    public boolean darDeAlta(String nombre, String apellido, ContenedorEmpleados contenedor)
    {
        boolean rta= false;
        Empleado us=contenedor.buscar(nombre,apellido);
        if(us!= null)
        {
            us.darDeAlta();
            rta= true;
        }
        return rta;
    }
    // agregar Usuario
    public boolean agregarUsuario(String nombre, String apellido, String password,String log, ContenedorEmpleados contenedor)
    {
        boolean rta= contenedor.agregar(new Administrador(contenedor.contar(),nombre,apellido,password,log),getLog());

        if(rta== true)
        {
            contenedor.agregarLog(log);
        }
        return rta;
    }
    public boolean agregarUsuario(String nombre, String apellido, String password, ContenedorEmpleados contenedor)
    {
        boolean rta= contenedor.agregar(new Cajero(contenedor.contar(),nombre,apellido,password),getLog());
        return rta;
    }

    public StringBuilder listar(ContenedorEmpleados contenedor)
    {
        return  contenedor.listar(getLog());
    }
    @Override
    public String toString() {
        return super.toString()+ " " + " puesto: administrador";
    }


}
