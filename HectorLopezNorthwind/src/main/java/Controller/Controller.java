package Controller;

import DAO.*;
import Model.Producto;
import Model.ProductoJSON;
import Model.ProductoResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public void leerUrl() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL url = new URL("https://dummyjson.com/products");
            ProductoResponse response = mapper.readValue(url, ProductoResponse.class);

            ProductoDAOimp productoDAOimp = new ProductoDAOimp();
                for (ProductoJSON productoJSON : response.getProducts()) {
                    productoDAOimp.insertarProductoJson(productoJSON);
                }

            } catch (MalformedURLException e) {
            System.out.println("Error en al URL");
            throw new RuntimeException(e);
        } catch (StreamReadException e) {
            System.out.println("Error en la lectura del stream");
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            System.out.println("Error en el mapeo de datos");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida");
            throw new RuntimeException(e);
        }

    }
    public void insertarUsuario(){
        Scanner scanner = new Scanner(System.in);
        EmpladoDAOImp empladoDAOImp = new EmpladoDAOImp();

        int id_empleado = empladoDAOImp.obtenerUltimoId();

        System.out.println("Indica el nombre del empleado: ");
        String nombre = scanner.nextLine();
        empladoDAOImp.insertarEmpleado(id_empleado,nombre);
    }

    public void insertaPedido(){
        Scanner scanner = new Scanner(System.in);

        PedidoDAOImp pedidoDAOImp = new PedidoDAOImp();
        int id_pedido = pedidoDAOImp.obtenerUltimoIdPedido();

        System.out.println("Indica numero de producto: ");
        int id_producto = scanner.nextInt();
        pedidoDAOImp.insertarPedido(id_pedido,id_producto);
    }

    public List<Producto> listaProductosPrecio(){
        Scanner scanner = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAOimp();

        System.out.println("Indica el precio minimo: ");
        int precioMinimo = scanner.nextInt();

        System.out.println("Indica el precio maximo: ");
        int precioMaximo = scanner.nextInt();

        List<Producto> productoList = productoDAO.obtenerListaProductoFiltrada(precioMinimo,precioMaximo);
        for (Producto producto : productoList){
            System.out.println(producto.toString());
        }
        return  productoList;
    }

    public void insertarLoteFiltrado(){
        ProductoFavDao productoFavDao = new ProductoFavImp();
        ProductoDAO productoDAO = new ProductoDAOimp();

        List<Producto> productoList = listaProductosPrecio();
        int id_producto_fav = productoFavDao.obtenerUltimoIdProductoFav() + 1;

        for (Producto producto : productoList){
            productoFavDao.insertarProductoFav(id_producto_fav,producto);
            id_producto_fav++;
        }
    }


}

