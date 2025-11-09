package br.edu.unicesumar.controller;

import br.edu.unicesumar.model.Cliente;
import br.edu.unicesumar.model.Pedido;
import br.edu.unicesumar.model.Produto;
import br.edu.unicesumar.model.ItemPedido;
import br.edu.unicesumar.model.StatusPedido;
import br.edu.unicesumar.service.PedidoService;
import br.edu.unicesumar.service.ClienteService;
import br.edu.unicesumar.service.ProdutoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoController {

    // Componentes de Interface FXML
    @FXML private ComboBox<String> cmbCliente;
    @FXML private ComboBox<String> cmbProduto;
    @FXML private TextField txtQuantidade;
    @FXML private ListView<ItemPedido> lvItens; // MUDANÇA: Tipo genérico deve ser ItemPedido
    @FXML private Label lblTotal;
    @FXML private Label lblStatus;

    // Services
    private PedidoService pedidoService = new PedidoService();
    private ClienteService clienteService = new ClienteService();
    private ProdutoService produtoService = new ProdutoService();
    
    // Variáveis de Estado
    private Pedido pedidoEmAndamento;
    // MUDANÇA: A lista observável deve ser do tipo ItemPedido
    private ObservableList<ItemPedido> itensTemporarios = FXCollections.observableArrayList(); 

    @FXML
    public void initialize() {
        // 1. VINCULAÇÃO CORRIGIDA: A ListView recebe a lista de objetos ItemPedido
        lvItens.setItems(itensTemporarios);
        
        // 2. CORREÇÃO PRINCIPAL: Define como o objeto ItemPedido será renderizado na lista (Cell Factory)
        lvItens.setCellFactory(param -> new ListCell<ItemPedido>() {
            @Override
            protected void updateItem(ItemPedido item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    double subtotal = item.getProduto().getPreco() * item.getQuantidade();
                    String texto = String.format("%s x %d = R$ %.2f", 
                        item.getProduto().getNome(), item.getQuantidade(), subtotal);
                    setText(texto);
                }
            }
        });
        
        carregarCombos();
        iniciarNovoPedido();
    }
    
    private void carregarCombos() {
        // Carrega Clientes (Simplificado: só exibe o nome)
        // Assumindo que o método findAll() existe e retorna List<Cliente>
        List<Cliente> clientes = clienteService.findAll(); 
        cmbCliente.setItems(FXCollections.observableArrayList(clientes.stream().map(Cliente::getNome).toList()));

        // Carrega Produtos (Simplificado: só exibe o nome)
        // Assumindo que o método findAll() existe e retorna List<Produto>
        List<Produto> produtos = produtoService.findAll(); 
        cmbProduto.setItems(FXCollections.observableArrayList(produtos.stream().map(Produto::getNome).toList()));
    }
    
    private void iniciarNovoPedido() {
        this.pedidoEmAndamento = new Pedido();
        this.itensTemporarios.clear();
        this.pedidoEmAndamento.setItens(itensTemporarios);
        lblTotal.setText("R$ 0.00");
        lblStatus.setText("Novo pedido iniciado.");
    }

    @FXML
    public void handleAdicionarItem() {
        try {
            if (cmbProduto.getValue() == null) throw new IllegalArgumentException("Selecione um produto.");
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva.");

            // Lógica real: Buscar produto pelo nome selecionado
            Produto produtoSelecionado = produtoService.findByName(cmbProduto.getValue()); 
            if (produtoSelecionado == null) throw new RuntimeException("Produto não encontrado.");

            ItemPedido novoItem = new ItemPedido(produtoSelecionado, quantidade);
            
            // Adiciona à lista do pedido em andamento (e ao ObservableList)
            this.pedidoEmAndamento.addItem(novoItem); 
            
            // Recalcula e atualiza a View
            this.pedidoEmAndamento.setValorTotal(pedidoService.calcularValorTotal(this.itensTemporarios));
            lblTotal.setText("R$ " + String.format("%.2f", this.pedidoEmAndamento.getValorTotal()));

            lblStatus.setText("Item adicionado.");
            txtQuantidade.clear();
            
        } catch (NumberFormatException e) {
            exibirAlerta(AlertType.ERROR, "Erro", "Quantidade deve ser um número.");
        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro", e.getMessage());
        }
    }

    @FXML
    public void handleFinalizarPedido() {
        try {
            if (cmbCliente.getValue() == null) throw new IllegalArgumentException("Selecione um cliente.");
            
            // Lógica real: Buscar cliente pelo nome selecionado
            Cliente clienteSelecionado = clienteService.findByName(cmbCliente.getValue()); 

            this.pedidoEmAndamento.setCliente(clienteSelecionado);
            this.pedidoEmAndamento.setStatus(StatusPedido.PAGO); // Define o status final antes de salvar

            // Persiste o pedido (e todos os itens em cascata)
            pedidoService.save(this.pedidoEmAndamento);

            exibirAlerta(AlertType.INFORMATION, "Sucesso", "Pedido #" + this.pedidoEmAndamento.getId() + " salvo com sucesso!");
            iniciarNovoPedido(); // Inicia um novo pedido após salvar
            
        } catch (IllegalArgumentException e) {
            exibirAlerta(AlertType.WARNING, "Validação", e.getMessage());
        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro de Persistência", "Falha ao finalizar o pedido: " + e.getMessage());
        }
    }

    private void exibirAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}