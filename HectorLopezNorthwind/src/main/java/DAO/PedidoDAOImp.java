package DAO;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PedidoDAOImp implements PedidoDAO {
    Connection connection = DBConnection.getConnection();


    @Override
    public boolean insertarPedido(int id_pedido, int id_producto) {

        String sql = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)",
                "pedidos",
                "id_pedido",
                "id_producto");
        try(

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            // aqui lo que hago es que si el id_empleado es 0, le asigno 1, si no, le asigno el id_empleado +1
            preparedStatement.setInt(1, id_pedido + 1);
            preparedStatement.setInt(2, id_producto);
            int filasInsertadas = preparedStatement.executeUpdate();
            if (filasInsertadas > 0){
                System.out.println("Pedido insertado correctamente");
                return true;
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al insertar el pedido: " + e.getMessage());
        }
        return false;
    }


    @Override
    public int obtenerUltimoIdPedido() {
        String query = "SELECT MAX(id_pedido)  FROM northwind.pedidos";

        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al obtener el ultimo ID de pedidos: " + e.getMessage());
        }
        return 0;
    }
}
