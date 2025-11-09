package br.edu.unicesumar.dao;

import br.edu.unicesumar.model.ItemPedido;
import java.util.List;
import jakarta.persistence.TypedQuery;

// ItemPedidoDAO herda as operações CRUD para a Entidade ItemPedido
public class ItemPedidoDAO extends DAO<ItemPedido> {
    
    public ItemPedidoDAO() {
        super();
    }
    
    /*
    Geralmente, você não usa o SAVE ou DELETE neste DAO, pois o Pedido faz isso.
    Você usaria este DAO para consultas específicas, como:
    */
    
    // Método para listar todos os itens de um pedido específico
    public List<ItemPedido> findByPedidoId(int pedidoId) {
        try {
            // JPQL: Assume que o Pedido tem um atributo 'id'
            TypedQuery<ItemPedido> query = em.createQuery(
                "SELECT ip FROM ItemPedido ip WHERE ip.pedido.id = :id", ItemPedido.class
            );
            query.setParameter("id", pedidoId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}