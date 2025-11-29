package DAO;

public interface PedidoDAO {
    public boolean insertarPedido(int id_pedido, int id_producto);
    public int obtenerUltimoIdPedido();
}
