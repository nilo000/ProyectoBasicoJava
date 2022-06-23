package app;
import app.Archivos.ManejoDeArchivos;
import app.Contenedores.Stock;
import app.GestionDeCaja.Producto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class App {
    private Stock<Producto> heladeraStock;
    private Stock<Producto> carnesStock;
    private Stock<Producto> limpiezaStock;
    private double recaudacion;
    private ArrayList<Ticket> tickets;

    public App()  {

        tickets = new ArrayList<>();
        heladeraStock = new Stock<>(100);
        carnesStock = new Stock<>(100);
        limpiezaStock = new Stock<>(100);
    }


    public void agregarForzado(Producto producto)
    {
        switch (producto.getSeccion()) {
            case "heladera" -> heladeraStock.agregar(producto);
            case "carnes" -> carnesStock.agregar(producto);
            case "limpieza" -> limpiezaStock.agregar(producto);
        }
    }

    public void comprar(Producto productoElegido, Ticket ticket)
    {
        if (productoElegido != null) {
            ticket.generarProducto(productoElegido);
            productoElegido.setStock(productoElegido.getStock()-1);
            recaudacion+= productoElegido.getPrecio();
        }
    }

    public void finalizarCompra(Ticket ticket)
    {
        tickets.add(ticket);
    }


    public Producto devolverProducto(int codigo)
    {
        Producto producto;
        if(heladeraStock.buscar(codigo)!=null) {
            producto= heladeraStock.buscar(codigo);
            return producto;
        }else if(carnesStock.buscar(codigo)!=null){
            producto= carnesStock.buscar(codigo);
            return producto;
        }else if(limpiezaStock.buscar(codigo)!=null){
            producto= limpiezaStock.buscar(codigo);
            return producto;
        }else return null;
    }

    public String mostrarHeladera()
    {
        return heladeraStock.mostrar();
    }
    public String mostrarCarne()
    {
        return carnesStock.mostrar();
    }
    public String mostrarLegumbres()
    {
        return limpiezaStock.mostrar();
    }

    public String mostrar()
    {
        return "heladera :\n"+ heladeraStock.mostrar() + "\n"+"carnes :\n"+ carnesStock.mostrar()+"\nlimpieza :\n"+ limpiezaStock.mostrar();
    }

    public String agregar(String nombre,Integer codigo, double precio, int stock, String unidadMedida, double cantidadMedida,String seccion,String fechaVencimiento)
    {
        Producto producto= new Producto(nombre,codigo,precio,stock,unidadMedida,cantidadMedida,seccion,fechaVencimiento);
        String agregadoCorrecto = "producto agregado";
        String resultado = "seccion no encontrada, intente nuevamente";

        switch (seccion) {
            case "heladera" -> {
                heladeraStock.agregar(producto);
                resultado = agregadoCorrecto;
            }
            case "carnes" -> {
                carnesStock.agregar(producto);
                resultado = agregadoCorrecto;
            }
            case "limpieza" -> {
                limpiezaStock.agregar(producto);
                resultado = agregadoCorrecto;
            }
        }
        return resultado;
    }

    public String eliminar(int codigo)
    {
        Producto producto = devolverProducto(codigo);
        String retorno = "se quito el producto";
        String resultado = "error en quitar el producto";

        if (heladeraStock.quitar(producto))
        {
            resultado= retorno;
        }else if(carnesStock.quitar(producto)){
            resultado= retorno;
        }
        else if(limpiezaStock.quitar(producto)){
            resultado= retorno;
        }
        return resultado;
    }



    public boolean modificarProducto(int codigo, String nombreM, int codigoM, double precioM, int stockM, String unidadDeMedidaM, double cantidadDeMedidaM, String fechaNuevo)
    {
        boolean on = false;
        if(heladeraStock.buscar(codigo)!=null) {
            heladeraStock.modificarNombreProducto(codigo,nombreM);
            heladeraStock.modificarCodigoProducto(codigo,codigoM);
            heladeraStock.modificarPrecioProducto(codigo,precioM);
            heladeraStock.modificarStockProducto(codigo,stockM);
            heladeraStock.modificarMedidaProducto(codigo,unidadDeMedidaM,cantidadDeMedidaM);
            heladeraStock.modificarVencimientoAlimento(codigo,fechaNuevo);
            on = true;
        }else if(carnesStock.buscar(codigo)!=null){
            carnesStock.modificarNombreProducto(codigo,nombreM);
            carnesStock.modificarCodigoProducto(codigo,codigoM);
            carnesStock.modificarPrecioProducto(codigo,precioM);
            carnesStock.modificarStockProducto(codigo,stockM);
            carnesStock.modificarMedidaProducto(codigo,unidadDeMedidaM,cantidadDeMedidaM);
            carnesStock.modificarVencimientoAlimento(codigo,fechaNuevo);
            on = true;
        }else if(limpiezaStock.buscar(codigo)!=null){
            limpiezaStock.modificarNombreProducto(codigo,nombreM);
            limpiezaStock.modificarCodigoProducto(codigo,codigoM);
            limpiezaStock.modificarPrecioProducto(codigo,precioM);
            limpiezaStock.modificarStockProducto(codigo,stockM);
            limpiezaStock.modificarMedidaProducto(codigo,unidadDeMedidaM,cantidadDeMedidaM);
            limpiezaStock.modificarVencimientoAlimento(codigo,fechaNuevo);
            on = true;
        }
        return on;
    }

    public boolean agregarStock(int codigo,int cantidad)
    {
        boolean on = false;
        if(heladeraStock.buscar(codigo)!=null) {
            heladeraStock.agregarStockProducto(codigo,cantidad);
            on = true;
        }else if(carnesStock.buscar(codigo)!=null){
            carnesStock.agregarStockProducto(codigo,cantidad);
            on = true;
        }else if(limpiezaStock.buscar(codigo)!=null){
            limpiezaStock.agregarStockProducto(codigo,cantidad);
            on = true;
        }
        return on;
    }

    public boolean quitarStock(int codigo,int cantidad)
    {
        boolean on = false;
        if(heladeraStock.buscar(codigo)!=null) {
            carnesStock.quitarStockProducto(codigo,cantidad);
            on = true;
        }else if(carnesStock.buscar(codigo)!=null){
            carnesStock.quitarStockProducto(codigo,cantidad);
            on = true;
        }else if(limpiezaStock.buscar(codigo)!=null){
            limpiezaStock.quitarStockProducto(codigo,cantidad);
            on = true;
        }
        return on;
    }

    public double getRecaudacion() {
        return recaudacion;
    }
    public void pasarStock()
    {
        ManejoDeArchivos arch = new ManejoDeArchivos("stock.bin");

        ArrayList<Producto> productos = arch.leerP();

        for(Producto p: productos)
        {
            if(p.getSeccion().equals("heladera"))
            {
               heladeraStock.agregar(p) ;
            }else if(p.getSeccion().equals("carnes"))
            {
                carnesStock.agregar(p) ;
            }else if(p.getSeccion().equals("limpieza"))
            {
                limpiezaStock.agregar(p) ;
            }
        }
    }

    public ArrayList<Producto> listarStock()
    {
        ArrayList<Producto> productos = new ArrayList<>();

        Iterator heladera = heladeraStock.iterator();

        while (heladera.hasNext())
        {
            Map.Entry<Integer,Producto> en = (Map.Entry<Integer, Producto>) heladera.next();
            productos.add(en.getValue());
        }

        Iterator carnes= carnesStock.iterator();

        while (carnes.hasNext())
        {
            Map.Entry<Integer,Producto> en = (Map.Entry<Integer, Producto>) carnes.next();
            productos.add(en.getValue());
        }

        Iterator limpieza = limpiezaStock.iterator();

        while (limpieza.hasNext())
        {
            Map.Entry<Integer,Producto> en = (Map.Entry<Integer, Producto>) limpieza.next();
            productos.add(en.getValue());
        }
        return productos;
    }

    public void cerrarCaja()
    {
        ManejoDeArchivos archivoStock = new ManejoDeArchivos("stock.bin");
        archivoStock.escribirArchivoP(listarStock());
        archivoStock.escribirRecaudacion(getRecaudacion());
    }
    public void abrirCaja()
    {
        ManejoDeArchivos archivoStock = new ManejoDeArchivos("stock.bin");
        pasarStock();
        recaudacion = archivoStock.sacarRecaudacion();
    }
}
