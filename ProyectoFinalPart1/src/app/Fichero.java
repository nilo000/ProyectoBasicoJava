package app;

import app.Archivos.ManejoDeArchivos;
import app.Contenedores.ContenedorEmpleados;
import app.Excepciones.ExcepcionEstado;
import app.Excepciones.ExcepcionNombreUsuario;
import app.Excepciones.ExcepcionPassword;
import app.Usuarios.Administrador;
import app.Usuarios.Cajero;
import app.Usuarios.Empleado;

import java.util.ArrayList;

public class Fichero {

    private ContenedorEmpleados listado;

    public Fichero(int cant) {
        this.listado = new ContenedorEmpleados(cant);
    }

    public Fichero() {
        ManejoDeArchivos archivoEmpleados= new ManejoDeArchivos("archivoEmpleados.bin");
        this.listado = archivoEmpleados.leer();
    }

    ///ingreso de usuario
    public Empleado ingresoUsuario(String nombre, String apellido, String password) throws ExcepcionPassword,ExcepcionNombreUsuario, ExcepcionEstado
    {
        Empleado buscado= listado.buscar(nombre,apellido);
        Empleado rta= null;
        if(buscado!= null)
        {
            if(buscado.getPassword().equals(password))
            {
                rta= buscado;
            }
            else
            {
                throw new ExcepcionPassword();
            }
        }else
        {
            throw new ExcepcionNombreUsuario();
        }

        if(!rta.isEstado())
        {
            throw new ExcepcionEstado();

        }
        return rta;
    }

    public boolean darDeBaja(int id,Administrador us)
    {
       boolean rta= us.darDeBaja(id,listado);
       return rta;
    }

    public boolean darDeBaja(String nombre, String apellido,Administrador us)
    {
        boolean rta= us.darDeBaja(nombre,apellido,listado);
        return rta;
    }
    //dar de alta
    public boolean darDeAlta(int id,Administrador us)
    {
        boolean rta= us.darDeAlta(id,listado);
        return rta;
    }

    public boolean darDeAlta(String nombre, String apellido,Administrador us)
    {
        boolean rta= us.darDeAlta(nombre,apellido,listado);
        return rta;
    }
    // agregar Usuario
    public boolean agregarUsuario(String nombre, String apellido, String password,String log,Administrador us)
    {
        boolean rta= us.agregarUsuario(nombre,apellido,password,log,listado);
        return rta;
    }
    public boolean agregarUsuario(String nombre, String apellido, String password, Administrador us)
    {
        boolean rta= us.agregarUsuario(nombre,apellido,password,listado);
        return rta;
    }

    // buscar usuario
    public boolean existe(int id)
    {
        return listado.existe(id);
    }

    public StringBuilder listar(Administrador admin)
    {
       return  admin.listar(listado);
    }

    /// agregar ingreso y egreso de usuario
    public ArrayList<Empleado> pasarALista()
    {
        return listado.pasarAlistado();
    }


    public void cerrarFichero(String nombre)
    {
        ManejoDeArchivos archivoEmpleados= new ManejoDeArchivos(nombre);
        archivoEmpleados.escribirArchivo(pasarALista());

    }
}
