package app.Contenedores;

import app.Interfaces.IColeccion;
import app.GestionDeCaja.Producto;

import java.util.*;
import java.util.Map.Entry;


public class Stock<T extends Producto> {

    private HashMap <Integer ,T> stock;
    private int capacidad;
    private int ocupado;

    public Stock(int capacidad) {
        stock = new HashMap<>();
        this.capacidad = capacidad;
        ocupado = 0;
    }
    public Stock(HashMap<Integer,T> listado,int ocupado)
    {
        stock = listado;
        this.ocupado = ocupado;
        capacidad = 100;
    }


    public boolean agregar(T producto) { // devulve false si no lo agrego
        boolean agregado = false;

        Integer codigo = producto.getCodigo();

        if(ocupado<capacidad && !(stock.containsKey(codigo))) {
            stock.put(codigo, producto);
            agregado = true;
            ocupado = ocupado + 1;
        }


        return agregado;
    }

    public boolean quitar(T producto) { // devuelve false si no lo quito
        boolean quitado = false;

        Integer codigo = producto.getCodigo();

        if(stock.remove(codigo) != null) {
            quitado = true;
            ocupado--;
        }

        return quitado;
    }

    public String mostrar() { // devuelve un string con la lista de productos
        StringBuilder mapa = new StringBuilder();

        Iterator iterador = stock.entrySet().iterator();

        while(iterador.hasNext()) {
            Map.Entry<Integer, T> entrada = (Entry<Integer, T>) iterador.next();
            Integer clave = entrada.getKey();
            T producto = entrada.getValue();
            mapa.append(producto.toString()+"\n");
        }

        String lista = mapa.toString();
        return lista ;
    }

    public T buscar(Integer clave) { //devuelve null si no lo encuentra
        T rta = null;
        if(stock.containsKey(clave)) {
            rta = stock.get(clave);
        }

        return rta;
    }

    public int espacioDisponible() {
        return capacidad-ocupado;
    }

    public boolean modificarNombreProducto(Integer codigo, String nombre) {

        boolean modificado = false;

        if(stock.containsKey(codigo)) {
            T aModificar = stock.get(codigo);

            aModificar.setNombre(nombre);

            stock.put(codigo, aModificar);

            modificado = true;
        }

        return modificado;
    }

    public boolean modificarCodigoProducto(Integer codigo, Integer codigoNuevo) {

        boolean modificado = false;

        if(stock.containsKey(codigo)) {
            T aModificar = stock.get(codigo);

            aModificar.setCodigo(codigoNuevo);

            stock.remove(codigo);

            stock.put(codigoNuevo, aModificar);

            modificado = true;
        }

        return modificado;
    }


    public boolean modificarPrecioProducto(Integer codigo, double precio) {

        boolean modificado = false;

        if(stock.containsKey(codigo)) {
            T aModificar = stock.get(codigo);

            aModificar.setPrecio(precio);

            stock.put(codigo, aModificar);

            modificado = true;
        }

        return modificado;
    }

    public boolean modificarStockProducto(Integer codigo, int stockNuevo) {

        boolean modificado = false;

        if(stock.containsKey(codigo)) {
            T aModificar = stock.get(codigo);

            aModificar.setStock(stockNuevo);

            stock.put(codigo, aModificar);

            modificado = true;
        }

        return modificado;
    }

    public boolean modificarMedidaProducto(Integer codigo, String unidad, double cant) {

        boolean modificado = false;

        if(stock.containsKey(codigo)) {
            T aModificar = stock.get(codigo);

            aModificar.setUnidadDeMedida(unidad);
            aModificar.setCantidadDeMedida(cant);

            stock.put(codigo, aModificar);

            modificado = true;
        }

        return modificado;
    }

    public boolean modificarVencimientoAlimento(Integer codigo, String vencimiento) {

        boolean modificado = false;

        if(stock.containsKey(codigo)) {
            T aModificar = stock.get(codigo);

            aModificar.setFechaDeVencimiento(vencimiento);

            stock.put(codigo, aModificar);

            modificado = true;
        }

        return modificado;
    }

    public boolean agregarStockProducto(Integer codigo, int stockagregado) {

        boolean modificado = false;

        if(stock.containsKey(codigo)) {
            T aModificar = stock.get(codigo);

            int stockNuevo = aModificar.getStock() + stockagregado;

            aModificar.setStock(stockNuevo);

            stock.put(codigo, aModificar);

            modificado = true;
        }

        return modificado;
    }

    public boolean quitarStockProducto(Integer codigo, int stockquitado) {

        boolean modificado = false;

        int stockOriginal = stock.get(codigo).getStock();

        if(stock.containsKey(codigo) && stockOriginal>=stockquitado) {
            T aModificar = stock.get(codigo);

            int stockNuevo = aModificar.getStock() - stockquitado;

            aModificar.setStock(stockNuevo);

            stock.put(codigo, aModificar);

            modificado = true;
        }

        return modificado;
    }

    public Iterator iterator()
    {
        return  stock.entrySet().iterator();
    }
}