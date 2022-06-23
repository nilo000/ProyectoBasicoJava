package app.GestionDeCaja;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable {
    //ATRIBUTOS
    private String nombre;
    private Integer codigo;
    private double precio;
    private int stock;
    private String unidadDeMedida; // kg, lt, gr, unidades
    private double cantidadDeMedida; // cantidad de la unidad de medida
    private String seccion;
    private String fechaDeVencimiento;

    public Producto(String nombre, Integer codigo, double precio, int stock, String unidadDeMedida, double cantidadDeMedida,String seccion,String fechaDeVencimiento) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
        this.unidadDeMedida = unidadDeMedida;
        this.cantidadDeMedida = cantidadDeMedida;
        this.seccion=seccion;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }


    public Integer getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public String medida() {
        return (cantidadDeMedida+" "+unidadDeMedida);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSeccion() {
        return seccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public double getCantidadDeMedida() {
        return cantidadDeMedida;
    }

    public void setCantidadDeMedida(double cantidadDeMedida) {
        this.cantidadDeMedida = cantidadDeMedida;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(String fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    //METODOS


    @Override
    public String toString() {
        return
                nombre + "\n"+
                        " codigo=" + codigo +"\n"+
                        " precio=" + precio +"\n"+
                        " stock=" + stock +"\n"+
                        " unidadDeMedida='" + unidadDeMedida + "\n"+
                        " cantidadDeMedida=" + cantidadDeMedida +"\n"+
                        " seccion='" + seccion + "\n"+
                        " fechaDeVencimiento=" + fechaDeVencimiento;
    }

    public JSONObject productoToJson() {
        JSONObject productojson = new JSONObject();

        try {
            productojson.put("nombre", this.nombre);
            productojson.put("precio", this.precio);
            productojson.put("fecha de vencimiento", this.fechaDeVencimiento);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return productojson;
    }
    public JSONObject productoToJsonCompleto() {
        JSONObject productojson = new JSONObject();

        try {
            productojson.put("nombre", this.nombre);
            productojson.put("codigo",codigo);
            productojson.put("precio",precio);
            productojson.put("stock",stock);
            productojson.put("unidades de medida",unidadDeMedida);
            productojson.put("cantidad de medida",cantidadDeMedida);
            productojson.put("seccion",seccion);
            productojson.put("precio", this.precio);
            productojson.put("fecha de vencimiento", this.fechaDeVencimiento.toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return productojson;
    }
}