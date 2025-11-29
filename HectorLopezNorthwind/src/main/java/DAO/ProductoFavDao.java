package DAO;

import Model.Producto;

public interface ProductoFavDao {
    public int obtenerUltimoIdProductoFav();
    public boolean insertarProductoFav(int id_producto_fav , Producto producto);
}
