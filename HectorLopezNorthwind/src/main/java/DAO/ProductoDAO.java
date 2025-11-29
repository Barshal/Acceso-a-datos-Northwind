package DAO;

import Model.Producto;
import Model.ProductoJSON;

import java.util.List;

public interface ProductoDAO {
    public boolean insertarProductoJson(ProductoJSON productoJSON);
    public List<Producto> obtenerListaProductoFiltrada(int precioMinimo, int precioMaximo);
}
