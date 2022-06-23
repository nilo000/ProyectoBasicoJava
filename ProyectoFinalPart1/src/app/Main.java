package app;
import app.Archivos.ManejoDeArchivos;
import app.Excepciones.ExcepcionEstado;
import app.Excepciones.ExcepcionNombreUsuario;
import app.Excepciones.ExcepcionPassword;
import app.GestionDeCaja.Producto;
import app.Usuarios.Administrador;
import app.Usuarios.Cajero;
import app.Usuarios.Empleado;
import java.util.Scanner;

/**
 * abrir caja
 * cerrar caja x
 * importar historial
 * exportar historial
 * */
public class Main {

    static Scanner  sc;
    public static void main(String[] args) {

        sc= new Scanner(System.in);



        Fichero fichero = new Fichero();
        Empleado empleadoLogueado;

        ManejoDeArchivos m = new ManejoDeArchivos("recaudacion.bin");

        int op2= -1;
        int op=0;


        while(op2!=2)
        {
            System.out.println("1. ingresar \n2. salir");
            op2= sc.nextInt();
            if(op2== 1) {
                try {
                    System.out.println("Nombre: ");
                    String nombre = sc.next();
                    System.out.println("apellido: ");
                    String apellido = sc.next();
                    System.out.println("password: ");
                    String password = sc.next();


                    empleadoLogueado = fichero.ingresoUsuario(nombre, apellido, password);
                    System.out.println();
                } catch (ExcepcionPassword | ExcepcionNombreUsuario e ) {
                    System.out.println(e);
                    empleadoLogueado=null;

                } catch (ExcepcionEstado e)
                {
                    System.out.println(e);
                    empleadoLogueado=null;
                }


                if(empleadoLogueado!=null)
                {
                    op=1;
                }
                    while (op != 0) {
                        sc.nextLine();
                        if (empleadoLogueado instanceof Administrador admin) {
                            boolean rta1;
                            System.out.println("\n0.salir\n1. dar de baja con id\n2.dar de baja con nombre y apellido\n3.dar de alta con id\n4.dar de alta con nombre y apellido\n5.agregar administrador\n6.agregar Cajero\n7.listar");
                            System.out.println("ingrese opción: ");
                            op = sc.nextInt();

                            switch (op) {
                                case 0:
                                    fichero.cerrarFichero("archivoEmpleados.bin");
                                    break;
                                case 1:
                                    System.out.println("id usuario: ");
                                    int id = sc.nextInt();
                                    rta1 = fichero.darDeBaja(id, admin);

                                    if (!rta1) {
                                        System.out.println("id no encontrado");
                                    } else {
                                        System.out.println("dada de baja exitosa");
                                    }
                                    break;
                                case 2:
                                    System.out.println("nombre usuario: ");
                                    String nombre1 = sc.next();
                                    System.out.println("apellido usuario: ");
                                    String apellido1 = sc.next();
                                    rta1 = fichero.darDeBaja(nombre1, apellido1, admin);

                                    System.out.println(rta1);
                                    if (!rta1) {
                                        System.out.println("nombre y apellido no encontrado");
                                    } else {
                                        System.out.println("dada de baja exitosa");
                                    }
                                    break;
                                case 3:
                                    System.out.println("id usuario: ");
                                    id = sc.nextInt();
                                    rta1 = fichero.darDeAlta(id, admin);

                                    if (!rta1) {
                                        System.out.println("id no encontrado");
                                    } else {
                                        System.out.println("dada de alta exitosa");
                                    }
                                    break;
                                case 4:
                                    System.out.println("nombre usuario: ");
                                    nombre1 = sc.next();
                                    System.out.println("apellido usuario: ");
                                    apellido1 = sc.next();
                                    rta1 = fichero.darDeAlta(nombre1, apellido1, admin);

                                    System.out.println(rta1);
                                    if (!rta1) {
                                        System.out.println("nombre y apellido no encontrado");
                                    } else {
                                        System.out.println("dada de alta exitosa");
                                    }
                                    break;
                                case 5:
                                    System.out.println("nombre: ");
                                    String nombre = sc.next();
                                    System.out.println("apellido: ");
                                    String apellido = sc.next();
                                    System.out.println("password: ");
                                    String password = sc.next();
                                    System.out.println("log: ");
                                    String log = sc.next();
                                    rta1 = fichero.agregarUsuario(nombre, apellido, password, log, admin);
                                    if (rta1) {
                                        System.out.println("usuario agregado con exito");
                                    } else {
                                        System.out.println("usuario ya existente");
                                    }
                                    break;
                                case 6:
                                    System.out.println("nombre: ");
                                    nombre = sc.next();
                                    System.out.println("apellido: ");
                                    apellido = sc.next();
                                    System.out.println("password: ");
                                    password = sc.next();
                                    rta1 = fichero.agregarUsuario(nombre, apellido, password, admin);
                                    if (rta1) {
                                        System.out.println("usuario agregado con exito");
                                    } else {
                                        System.out.println("usuario ya existente");
                                    }
                                    break;
                                case 7:
                                    System.out.println(fichero.listar(admin));
                                    break;
                                default:
                                    System.out.println("opcion invalida: ");
                                    break;

                            }

                        } else if (empleadoLogueado instanceof Cajero) {

                            Ticket ticket;
                            App app = new App();
                            app.abrirCaja();
                            Scanner scan = new Scanner(System.in);

                            boolean boton = true;
                            while (boton) {
                                System.out.println("1: compra");
                                System.out.println("2: ver stock");
                                System.out.println("3: agregar a stock");
                                System.out.println("4: eliminar stock");
                                System.out.println("5: modificar producto");
                                System.out.println("6: modificar stock");
                                System.out.println("7: cerrar caja");
                                int op3 = scan.nextInt();
                                switch (op3) {
                                    case 1 -> {
                                        boolean on = true;
                                        ticket = new Ticket();
                                        while (on) {
                                            System.out.println("que seccion quiere seleccionar ?");
                                            System.out.println("1 : heladera");
                                            System.out.println("2 : carnes");
                                            System.out.println("3 : limpieza");
                                            int selec = scan.nextInt();
                                            System.out.println("seleccione el codigo del producto que desea comprar");
                                            if (selec == 1) {
                                                System.out.println(app.mostrarHeladera());
                                            } else if (selec == 2) {
                                                System.out.println(app.mostrarCarne());
                                            } else if (selec == 3) {
                                                System.out.println(app.mostrarLegumbres());
                                            } else {
                                                System.out.println("seccion incorrecta, intente de nuevo");
                                            }

                                            int codigoElegido = scan.nextInt();
                                            Producto elegido = app.devolverProducto(codigoElegido);
                                            if (elegido != null) {
                                                app.comprar(elegido,ticket);
                                            } else {
                                                System.out.println("codigo invalido");
                                            }
                                            System.out.println("quiere seguir comprando ? si/no");
                                            scan.nextLine();
                                            String seguir = scan.nextLine();
                                            if (seguir.equals("no")) {
                                                on = false;
                                                System.out.println("gracias por su compra");
                                                System.out.println("total a pagar : " + ticket.generarTicket());
                                                app.finalizarCompra(ticket);
                                            }
                                        }
                                    }
                                    case 2 -> {
                                        System.out.println("lista de productos disponilbes \n");
                                        System.out.println(app.mostrar());
                                    }
                                    case 3 -> {
                                        System.out.println("ingrese el nombre del producto");
                                        scan.nextLine();
                                        String nombre = scan.nextLine();
                                        System.out.println("ingrese el codigo ");
                                        int codigo = scan.nextInt();
                                        System.out.println("ingrese el precio");
                                        double precio = scan.nextDouble();
                                        System.out.println("ingrese la seccion");
                                        scan.nextLine();
                                        String seccion = scan.nextLine();
                                        System.out.println("ingrese el stock");
                                        int stock = scan.nextInt();
                                        scan.nextLine();
                                        System.out.println("ingrese la unidad de medida");
                                        String unidadMedida = scan.nextLine();
                                        System.out.println("ingrese la cantidad de medida");
                                        double cantidadMedida = scan.nextDouble();
                                        System.out.println("ingrese la fecha de vencimiento");
                                        System.out.println("dia");
                                        int dia = scan.nextInt();
                                        System.out.println("mes");
                                        int mes = scan.nextInt();
                                        System.out.println("año");
                                        int año = scan.nextInt();
                                        String fecha = dia +"-"+ mes+"-"+año;
                                        String agregado = app.agregar(nombre, codigo, precio, stock, unidadMedida, cantidadMedida, seccion,fecha);
                                        System.out.println(agregado);
                                    }
                                    case 4 -> {
                                        System.out.println(app.mostrar());
                                        System.out.println("ingrese el codigo del producto que quiera eliminar");
                                        int codigoEliminar = scan.nextInt();
                                        System.out.println(app.eliminar(codigoEliminar));
                                    }
                                    case 5 -> {
                                        System.out.println("elige el producto que quiera modificar");
                                        System.out.println(app.mostrar());
                                        int claveModificar = scan.nextInt();
                                        System.out.println("ingrese el nombre nuevo");
                                        scan.nextLine();
                                        String nombreNuevo = scan.nextLine();
                                        System.out.println("ingrese el codigo nuevo");
                                        int codigoNuevo = scan.nextInt();
                                        System.out.println("ingrese el precio nuevo");
                                        double precioNuevo = scan.nextDouble();
                                        System.out.println("ingrese la cantidad de stock nuevo");
                                        int stockNuevo = scan.nextInt();
                                        System.out.println("ingrese la nueva unidad de medida");
                                        scan.nextLine();
                                        String unidadNueva = scan.nextLine();
                                        System.out.println("ingrese la nueva cantidad de medida");
                                        double cantidadNueva = scan.nextDouble();
                                        System.out.println("fecha de vencimiento:");
                                        System.out.println("ingrese el nuevo dia");
                                        int dia = scan.nextInt();
                                        System.out.println("ingrese el nuevo mes");
                                        int mes = scan.nextInt();
                                        System.out.println("ingrese el nuevo año");
                                        int año = scan.nextInt();
                                        String nuevaFecha = dia +"-"+ mes+"-"+año;
                                        if (app.modificarProducto(claveModificar, nombreNuevo, codigoNuevo, precioNuevo, stockNuevo, unidadNueva, cantidadNueva,nuevaFecha)) {
                                            System.out.println(app.devolverProducto(codigoNuevo).toString());
                                        } else System.out.println("error al modificar producto");
                                    }
                                    case 6 ->{
                                        System.out.println(app.mostrar());
                                        System.out.println("ingrese la clave del producto que quiera modificar el stock");
                                        int codigo = scan.nextInt();
                                        System.out.println("1 : agregar stock");
                                        System.out.println("2 : quitar stock");
                                        int x = scan.nextInt();
                                        if (x ==1)
                                        {
                                            System.out.println("ingrese la cantidad que le quiere sumar al stock");
                                            int cantidad = scan.nextInt();
                                            if(app.agregarStock(codigo,cantidad))
                                            {
                                                System.out.println("producto modificado");
                                            }else System.out.println("error en modificar el producto");

                                        }else if(x==2){
                                            System.out.println("ingrese la cantidad que quiera quitar al stock");
                                            int cantidad2 = scan.nextInt();
                                            if(app.quitarStock(codigo,cantidad2))
                                            {
                                                System.out.println("producto modificado");
                                            }else System.out.println("error en modificar el producto");

                                        }else System.out.println("opcion invalida");
                                    }
                                    case 7 -> {
                                        boton = false;
                                        System.out.println("la recaudacion hasta el momento es de " + app.getRecaudacion());
                                        app.cerrarCaja();
                                        op= 0;

                                    }
                                    default -> System.out.println("opcion invalida, intente nuevamente");
                                }
                            }

                        }
                    }
                }

            }


        }





    /// ingreso del usuario


}
