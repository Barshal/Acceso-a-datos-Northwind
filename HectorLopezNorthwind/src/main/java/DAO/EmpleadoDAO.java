package DAO;

public interface EmpleadoDAO {
    public boolean insertarEmpleado(int id_empleado, String nombre);
    public int obtenerUltimoId();
}
