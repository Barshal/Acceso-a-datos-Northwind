package DAO;

import Database.DBConnection;
import Model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoFavImp implements ProductoFavDao{
    @Override
    public int obtenerUltimoIdProductoFav() {

        Connection connection = DBConnection.getConnection();
        String query = "SELECT MAX(id_producto_fav)  FROM northwind.productos_fav";

        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al obtener el ultimo ID de productos_fav: " + e.getMessage());
        }
        return 0;

    }

    @Override
    public boolean insertarProductoFav(int id_producto_fav , Producto producto) {

        Connection connection = DBConnection.getConnection();

        try{
            String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                    "productos_fav",
                    "id_producto_fav",
                    "id_producto");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id_producto_fav);
            preparedStatement.setInt(2, producto.getId_producto());

            int filasInsertadas = preparedStatement.executeUpdate();
            return true;

        } catch (java.sql.SQLException e) {
            System.out.println("Error al insertar el producto favorito en la base de datos: " + e.getMessage());
        }
        return false;
    }
}
