package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.ProdutoDAO;
import br.edu.unicesumar.model.Produto;
import java.util.List;

public class ProdutoService {
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public void save(Produto produto) {
        // Regras de negócio aqui (ex: verificar se o preço é positivo)
        produtoDAO.save(produto);
    }
    
    // MÉTODO ESSENCIAL para o CarrinhoService (Corrigindo o erro de compilação)
    public Produto findById(int id) {
        // Assume que seu DAO genérico (DAO.java) já tem o método findById(Class<T> clazz, Integer id)
        return produtoDAO.findById(Produto.class, id);
    }
    
    public List<Produto> findAll() {
        // Assume que seu DAO genérico (DAO.java) já tem o método findAll(Class<T> clazz)
        return produtoDAO.findAll(Produto.class);
    }

    // Você precisará deste método para o PedidoController!
    public Produto findByName(String nome) {
        // Lógica de busca por nome (consulta customizada no ProdutoDAO)
        return null; 
    }
}