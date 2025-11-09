package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.ItemPedidoDAO;
import br.edu.unicesumar.model.ItemPedido;
import java.util.List;

public class ItemPedidoService {

    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();

    // Este SAVE é geralmente redundante devido ao Cascade no Pedido
    public void save(ItemPedido itemPedido) {
        // Regra de Negócio: Por exemplo, verificar se a quantidade é positiva
        if (itemPedido.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade do item deve ser positiva.");
        }
        itemPedidoDAO.save(itemPedido);
    }

    public List<ItemPedido> findByPedidoId(int pedidoId) {
        // Usa a consulta específica que criamos no DAO
        return itemPedidoDAO.findByPedidoId(pedidoId);
    }
}