package br.edu.unicesumar.controller;

import br.edu.unicesumar.model.Carrinho;
import br.edu.unicesumar.model.ItemCarrinho;
import br.edu.unicesumar.service.CarrinhoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CarrinhoController {

    // Componentes FXML de Input (assumindo que há campos na View)
    @FXML
    private TextField txtProdutoId; 
    
    @FXML
    private TextField txtQuantidade;
    
    // O Carrinho Service lida com a lógica de negócio
    private CarrinhoService carrinhoService = new CarrinhoService();
    
    // VARIÁVEL LOCAL: O carrinho que está sendo manipulado
    private Carrinho carrinhoAtual; 

    // Método chamado na inicialização para carregar o carrinho (ex: pelo ID do cliente 1)
    public void initialize() {
        // Em um sistema real, o ID do cliente viria da sessão logada.
        this.carrinhoAtual = carrinhoService.getCarrinhoDoCliente(1); 
        System.out.println("Carrinho ID: " + carrinhoAtual.getId() + " carregado.");
    }

    // Método para adicionar um item ao carrinho (chamado por um botão AddItem)
    @FXML
    public void handleAdicionarItem() {
        try {
            int produtoId = Integer.parseInt(txtProdutoId.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            carrinhoService.adicionarItem(carrinhoAtual, produtoId, quantidade);
            
            // Atualizar a lista de itens na View (se houver)
            // ...

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(quantidade + " unidade(s) do Produto #" + produtoId + " adicionado(s) ao carrinho!");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            exibirErro("Quantidade ou ID do produto inválido.");
        } catch (IllegalArgumentException e) {
            exibirErro(e.getMessage());
        } catch (Exception e) {
            exibirErro("Erro ao adicionar item: " + e.getMessage());
        }
    }

    // Método para finalizar a compra (simula a criação do Pedido)
    @FXML
    public void handleFinalizarCompra() {
        if (carrinhoAtual.getItens().isEmpty()) {
            exibirErro("O carrinho está vazio. Adicione produtos antes de finalizar.");
            return;
        }
        
        // Aqui chamaria um método no Service para converter o Carrinho em um Pedido (que persiste no banco)
        // pedidoService.converterCarrinhoEmPedido(carrinhoAtual);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Compra Finalizada");
        alert.setContentText("O carrinho foi convertido em um Pedido com sucesso.");
        alert.showAndWait();
        
        // Limpar o carrinho após a finalização
        this.carrinhoAtual = carrinhoService.getCarrinhoDoCliente(1); 
    }
    
    // Utilitário
    private void exibirErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Ação Falhou");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}