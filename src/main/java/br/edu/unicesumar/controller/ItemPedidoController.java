package br.edu.unicesumar.controller;

import br.edu.unicesumar.model.ItemPedido;
import br.edu.unicesumar.model.Produto;
import br.edu.unicesumar.model.Pedido;
import br.edu.unicesumar.service.ItemPedidoService;
import br.edu.unicesumar.service.ProdutoService;
import br.edu.unicesumar.service.PedidoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import java.util.List;

public class ItemPedidoController {

    @FXML private TextField txtProdutoId;
    @FXML private TextField txtPedidoId; // Necessário para criar o ItemPedido
    @FXML private TextField txtQuantidade;
    @FXML private Label lblStatus;

    private ItemPedidoService itemPedidoService = new ItemPedidoService();
    private ProdutoService produtoService = new ProdutoService(); // Necessário para buscar Produto
    private PedidoService pedidoService = new PedidoService();   // Necessário para buscar Pedido

    @FXML
    public void handleSalvarItemPedido() {
        try {
            int produtoId = Integer.parseInt(txtProdutoId.getText());
            int pedidoId = Integer.parseInt(txtPedidoId.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            // 1. Busca as entidades pai (dependências)
            Produto produto = produtoService.findById(produtoId); // Assumindo que este método existe
            Pedido pedido = pedidoService.findById(pedidoId);     // Assumindo que este método existe

            if (produto == null || pedido == null) {
                 throw new IllegalArgumentException("Produto ou Pedido de referência não encontrado.");
            }

            // 2. Cria e configura o ItemPedido
            ItemPedido novoItem = new ItemPedido(produto, quantidade);
            novoItem.setPedido(pedido);

            // 3. Salva via Service
            itemPedidoService.save(novoItem);

            lblStatus.setText("Item Pedido #" + novoItem.getId() + " salvo com sucesso.");
            exibirAlerta(AlertType.INFORMATION, "Sucesso", "Item Pedido salvo.");

        } catch (NumberFormatException e) {
            exibirAlerta(AlertType.ERROR, "Erro de Input", "ID e Quantidade devem ser números.");
        } catch (IllegalArgumentException e) {
            exibirAlerta(AlertType.WARNING, "Validação", e.getMessage());
        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro de Persistência", "Falha ao salvar item: " + e.getMessage());
        }
    }

    // Utilitário
    private void exibirAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}