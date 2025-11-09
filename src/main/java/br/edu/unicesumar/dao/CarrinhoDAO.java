package br.edu.unicesumar.dao;

import br.edu.unicesumar.model.Carrinho;

// O CarrinhoDAO herda as operações save, update, delete, etc., do DAO genérico para a entidade Carrinho
public class CarrinhoDAO extends DAO<Carrinho> {
    
    // Construtor que chama o construtor da classe pai (DAO)
    public CarrinhoDAO() {
        super();
    }
}