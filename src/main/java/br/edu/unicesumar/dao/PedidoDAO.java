package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.model.Pedido;
import br.edu.unicesumar.model.StatusPedido;
import jakarta.persistence.TypedQuery;

public class PedidoDAO extends DAO<Pedido> {

    public PedidoDAO() {
        super();
    }
    
    public List<Pedido> findByStatus(StatusPedido status) {
        try {
            TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.status = :status", Pedido.class);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}