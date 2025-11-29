package DAO;

import Database.DBConnection;
import Model.Producto;
import Model.ProductoJSON;
import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimp implements ProductoDAO{

    Connection connection = DBConnection.getConnection();
    @Override
    public boolean insertarProductoJson(ProductoJSON productoJSON) {

        String sql = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
                "productos",
                "id_producto",
                "nombre",
                "descripcion",
                "cantidad",
                "precio");

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productoJSON.getId());
            preparedStatement.setString(2, productoJSON.getTitle());
            preparedStatement.setString(3, productoJSON.getDescription());
            preparedStatement.setInt(4, productoJSON.getStock());
            preparedStatement.setDouble(5, productoJSON.getPrice());

            int filasInsertadas = preparedStatement.executeUpdate();
            return true;

        } catch (java.sql.SQLException e) {
            System.out.println("Error al insertar el producto en la base de datos");
            return false;
    }
}

    @Override
    public List<Producto> obtenerListaProductoFiltrada(int precioMinimo, int precioMaximo) {
        //String query = "SELECT * FROM northwind.productos where precio <= " + precioMaximo;
        String query = "SELECT * FROM northwind.productos where precio >= ? and precio <= ?";

        List<Producto> listaProductos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, precioMinimo);
            preparedStatement.setInt(2, precioMaximo);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_producto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");

                Producto producto = new Producto(id_producto, nombre, descripcion, precio, cantidad);
                listaProductos.add(producto);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al obtener la lista de productos: " + e.getMessage());
        }
        return listaProductos;
    }
}
