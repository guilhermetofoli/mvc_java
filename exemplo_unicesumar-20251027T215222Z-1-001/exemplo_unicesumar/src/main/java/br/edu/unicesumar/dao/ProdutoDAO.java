package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.model.Produto;
import jakarta.persistence.TypedQuery;

// Herda as operações CRUD para a Entidade Produto
public class ProdutoDAO extends DAO<Produto> {

    public ProdutoDAO() {
        super();
    }
    
    // Exemplo de método específico para o Produto
    public List<Produto> findByCategoria(int categoriaId) {
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.categoria.id = :catId", Produto.class);
            query.setParameter("catId", categoriaId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}