package app.Usuarios;


import java.io.Serializable;

public  abstract class Empleado implements Serializable {
    private int id;

    private String nombre;
    private String apellido;
    private String password;
    private boolean estado;

    public Empleado(int id,String nombre, String apellido, String password) {
        this.id =id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.estado= true;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void darDeBaja()
    {
       setEstado(false);
    }
    public void darDeAlta()
    {
        setEstado(true);
    }
    @Override
    public String toString() {
        return "\n" + "Empleado: " + nombre+" "+ apellido+ " , Id: "+ id;
    }

    @Override
    public boolean equals(Object o) {
        boolean rta= false;
        if(o !=null)
        {
            if(o instanceof Empleado)
            {
                Empleado aux= (Empleado) o;

                if(aux.getId() == getId() )
                {
                    rta= true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
