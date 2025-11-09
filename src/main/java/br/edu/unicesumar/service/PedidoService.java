package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.PedidoDAO;
import br.edu.unicesumar.model.Pedido;
import br.edu.unicesumar.model.ItemPedido;
import java.util.List;

public class PedidoService {
    
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ProdutoService produtoService = new ProdutoService(); // Injeta a dependência

    public void save(Pedido pedido) {
        // Regra de Negócio: Cálculo Final de Valor e Verificação
        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Um pedido deve conter pelo menos um item.");
        }
        
        // **OPCIONAL:** Recalcular e setar o valor total antes de salvar
        pedido.setValorTotal(calcularValorTotal(pedido.getItens()));
        
        // Simplesmente delega a persistência (que salva os itens em cascata)
        pedidoDAO.save(pedido);
    }
    
    public List<Pedido> findAll() {
        // Assume que seu DAO genérico tem o método de listagem
        return pedidoDAO.findAll(Pedido.class); 
    }
    
    // Lógica para calcular o valor total (usada para garantir o valor correto)
    public double calcularValorTotal(List<ItemPedido> itens) {
        double total = 0.0;
        for (ItemPedido item : itens) {
            // Usa o preço unitário do produto na hora da venda
            total += item.getProduto().getPreco() * item.getQuantidade();
        }
        return total;
    }
    
    public Pedido findById(int id) {
        return pedidoDAO.findById(Pedido.class, id);
    }
}