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
}