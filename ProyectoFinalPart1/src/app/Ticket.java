package app;

import app.GestionDeCaja.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ticket {

    private static ArrayList<Producto> productos ;
    private double precioFinal ;


    public Ticket()
    {
        productos  = new ArrayList();
        precioFinal = 0;
    }


    public void generarProducto(Producto producto)
    {
        productos.add(producto);
        precioFinal += producto.getPrecio();
    }

    public JSONObject generarTicket()
    {
        JSONObject ticketJSON = new JSONObject();
        JSONArray productosJSON = new JSONArray();

        for (Producto p : productos)
        {
            productosJSON.put(p.productoToJson());
        }
        try {
            ticketJSON.put("precio final",precioFinal);
            ticketJSON.put("productos",productosJSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ticketJSON;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("productos \n");
        for (Producto p : productos)
        {
            builder.append(p.toString());
        }
        builder.append("\nprecio final: "+precioFinal);

        return builder.toString();
    }
}