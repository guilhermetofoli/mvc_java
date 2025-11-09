package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.CarrinhoDAO;
import br.edu.unicesumar.model.Carrinho;
import br.edu.unicesumar.model.ItemCarrinho;
import br.edu.unicesumar.model.Produto;
import java.util.List;

public class CarrinhoService {

    private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
    private ProdutoService produtoService = new ProdutoService(); // Para buscar o produto

    public Carrinho getCarrinhoDoCliente(int clienteId) {

        return new Carrinho(); 
    }

    public void save(Carrinho carrinho) {
        // Regra de Negócio: Calcular frete, aplicar cupons, etc. (se houver)
        // Antes de salvar, chama o DAO.
        carrinhoDAO.save(carrinho);
    }

    public void adicionarItem(Carrinho carrinho, int produtoId, int quantidade) {
        // Lógica: Busca o produto, verifica se já existe no carrinho, atualiza ou adiciona.
        Produto produto = produtoService.findById(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        
        // Crio um novo item e adiciono, mas a lógica de verificação de duplicidade ficaria aqui.
        ItemCarrinho novoItem = new ItemCarrinho(produto, quantidade);
        carrinho.addItem(novoItem); 
        
        carrinhoDAO.update(carrinho); // Salva as alterações no carrinho
    }

    public void removerItem(Carrinho carrinho, ItemCarrinho item) {
        carrinho.removeItem(item);
        carrinhoDAO.update(carrinho);
    }
}