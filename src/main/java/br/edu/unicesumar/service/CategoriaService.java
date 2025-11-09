package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.CategoriaDAO;
import br.edu.unicesumar.model.Categoria;
import java.util.List;

public class CategoriaService {

    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void save(Categoria categoria) { 
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) { 
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }
        categoriaDAO.save(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaDAO.findAll(Categoria.class);
    }

    public Categoria findById(int id) {
        return categoriaDAO.findById(Categoria.class, id);
    }
}