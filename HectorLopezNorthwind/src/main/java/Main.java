import Controller.Controller;
import DAO.EmpladoDAOImp;
import Model.ProductoJSON;
import Model.ProductoResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Controller controller = new Controller();

        while(true){
            System.out.println("Selecciona una opcion: ");
            System.out.println("1. Leer e insertar productos desde URL");
            System.out.println("2. Insertar empleado");
            System.out.println("3. Insertar pedido");
            System.out.println("4. Consultar productos indicando un rango de precio");
            System.out.println("5. Insertar lote de productos filtrados por precio en favoritos");
            System.out.println("6. Salir");
            System.out.println("----> ");

            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    controller.leerUrl();
                    break;
                case 2:
                    controller.insertarUsuario();
                    break;
                case 3:
                    controller.insertaPedido();
                    break;
                case 4:
                    controller.listaProductosPrecio();
                    break;
                case 5:
                    controller.insertarLoteFiltrado();
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicacion");
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida");
            }
        }//
    }
}
