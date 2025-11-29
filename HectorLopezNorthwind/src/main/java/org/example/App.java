package org.example;
import Model.Producto;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Producto producto = new Producto("Laptop", "High-end gaming laptop", 1500.00, 10);
        System.out.println( "Producto creado: " + producto.getNombre() + ", Precio: " + producto.getPrecio() );
    }
}
