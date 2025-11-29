package DAO;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmpladoDAOImp implements EmpleadoDAO{
    Connection connection = DBConnection.getConnection();

    @Override
    public boolean insertarEmpleado(int id_empleado, String nombre) {

        String sql = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)",
                "empleados",
                "id_empleado",
                "nombre");
        try(

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            // aqui lo que hago es que si el id_empleado es 0, le asigno 1, si no, le asigno el id_empleado +1
            preparedStatement.setInt(1, id_empleado + 1);
            preparedStatement.setString(2, nombre);
            int filasInsertadas = preparedStatement.executeUpdate();
            if (filasInsertadas > 0){
                System.out.println("Empleado insertado correctamente");
                return true;
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al insertar el empleado: " + e.getMessage());
        }
        return false;
    }

    @Override
    public int obtenerUltimoId() {
        String query = "SELECT MAX(id_empleado)  FROM northwind.empleados";

        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al obtener el ultimo ID de empleados: " + e.getMessage());
        }
        return 0;
    }
}

