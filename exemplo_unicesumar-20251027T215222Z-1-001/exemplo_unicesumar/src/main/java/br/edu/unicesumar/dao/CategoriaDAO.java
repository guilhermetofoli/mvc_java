package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.model.Categoria;
import jakarta.persistence.TypedQuery;

// Herda as operações CRUD para a Entidade Categoria
public class CategoriaDAO extends DAO<Categoria> {

    // Adiciona o construtor padrão (chama o construtor da classe pai)
    public CategoriaDAO() {
        super();
    }
    // Exemplo de método específico para Categoria
    public List<Categoria> listAll() {
        try {
            TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}