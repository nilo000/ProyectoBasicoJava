package app.Contenedores;

import app.Interfaces.IColeccion;
import app.Usuarios.Empleado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ContenedorEmpleados implements IColeccion {
    private HashMap<Integer, Empleado> listado;
    private  int cant;
    private ArrayList<String> listaDeLog;

    public ContenedorEmpleados(int cant) {
        this.listado = new HashMap<>();
        this.cant = cant;
        this.listaDeLog = new ArrayList<>();
    }
    public ContenedorEmpleados(int cant, HashMap<Integer,Empleado>listado,ArrayList<String> listaDeLog) {
        this.listado = listado;
        this.cant = cant;
        this.listaDeLog = listaDeLog;
    }
    /// confirmarLog
    public boolean confirmarLog(String log) {
        boolean rta= false;

        for (String p: listaDeLog)
        {
           if(log.trim().equals(p.trim()))
           {
               rta= true;
           }
        }
        return rta;
    }
    /// agregar
    public boolean agregar(Empleado us, String log)
    {
        boolean rta= false;

        Empleado buscado= buscar(us.getNombre(),us.getApellido());
        if(cant>listado.size() && !existe(us.getId())&& confirmarLog(log)&& buscado==null)
        {
            listado.put(us.getId(),us);
            rta= true;
        }
        return rta;

    }

    /// eliminar
    public boolean eliminar(int id , String log)
    {
        boolean rta= false;
        if(existe(id) && confirmarLog(log))
        {
            rta= true;
            listado.remove(id);
        }

        return rta;

    }
    /// existe
    public boolean existe(int id)
    {
        boolean rta= false;
        Empleado buscado= buscar(id);
        if(buscado!=null) {
            if (listado.containsKey(id) && buscado.isEstado()) {
                rta = true;
            }
        }
        return rta;
    }
    public boolean existe(String nombre, String apellido)
    {
        boolean rta= false;

        Empleado buscado= buscar(nombre,apellido);
        if(buscado!= null)
        {
            if(buscado.isEstado()) {
                rta = true;
            }
        }
        return rta;
    }
    ///contar
    public int contar()
    {
        return listado.size();
    }
    /// vacio
    public boolean vacio()
    {
        boolean rta= false;

        if(listado.size()==0)
        {
         rta= true;
        }
        return rta;
    }
    ///listar
    public StringBuilder listar(String log)
    {
        StringBuilder sb= new StringBuilder("");

        if(confirmarLog(log)) {
            Iterator it = listado.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<Integer, Empleado> e = (Map.Entry<Integer, Empleado>) it.next();

                sb.append(e.getValue().toString());
            }
        }
        return sb;
    }
    ///buscar
    public Empleado buscar(String nombre, String apellido)
    {
        Empleado buscado = null;


            Iterator it = listado.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<Integer, Empleado> e = (Map.Entry<Integer, Empleado>) it.next();

                if (e.getValue().getNombre().equalsIgnoreCase(nombre) && e.getValue().getApellido().equalsIgnoreCase(apellido)) {
                    buscado = e.getValue();

                }
            }
        return buscado;
    }


    public Empleado buscar(int id, String log)
    {
        Empleado buscado=null;
        if(listado.containsKey(id) && confirmarLog(log))
        {
            buscado=listado.get(id);
        }
        return buscado;
    }
    private Empleado buscar(int id)
    {
        Empleado buscado=null;
        if(listado.containsKey(id))
        {
            buscado=listado.get(id);
        }
        return buscado;
    }
    // acceso de admin

    public void agregarLog(String log)
    {
        listaDeLog.add(log);
    }

    public  ArrayList<Empleado> pasarAlistado()
    {
        Iterator it= listado.entrySet().iterator();
        ArrayList<Empleado> result= new ArrayList<Empleado>();
        while(it.hasNext())
        {
            Map.Entry<String,Empleado> en= (Map.Entry<String, Empleado>) it.next();
            result.add(en.getValue());
        }
        return result;
    }

    public  int espacioDisponible()
    {
        return cant-contar();
    }


}
