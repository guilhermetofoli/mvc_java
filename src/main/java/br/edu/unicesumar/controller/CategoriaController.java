package br.edu.unicesumar.controller;

import br.edu.unicesumar.model.Categoria;
import br.edu.unicesumar.service.CategoriaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.List;

public class CategoriaController {

    // Componentes de Interface FXML
    @FXML
    private TextField txtNomeCategoria; 
    
    @FXML
    private ListView<String> lvCategorias; // Para exibir as categorias salvas
    
    @FXML
    private Label lblStatus; 

    // Instancia o Service
    private CategoriaService categoriaService = new CategoriaService();
    
    // Lista observável para vincular à ListView
    private ObservableList<String> categoriasObservableList = FXCollections.observableArrayList();

    // Método de inicialização (chamado automaticamente pelo JavaFX)
    @FXML
    public void initialize() {
        lvCategorias.setItems(categoriasObservableList);
        carregarCategorias();
    }

    // Método para Salvar (Chamado por um botão no FXML)
    @FXML
    public void handleSalvarCategoria() {
        try {
            // 1. Coleta dados da View
            String nome = txtNomeCategoria.getText();

            // 2. Cria o objeto Model
            Categoria novaCategoria = new Categoria(nome); 

            // 3. Chama o Service (que valida e persiste)
            categoriaService.save(novaCategoria);

            // 4. Feedback e Atualização da View
            lblStatus.setText("Categoria salva com sucesso: " + nome);
            carregarCategorias(); // Recarrega a lista para mostrar a nova categoria
            txtNomeCategoria.clear();

        } catch (IllegalArgumentException e) {
            // Captura erros de validação do Service
            exibirAlerta(AlertType.WARNING, "Validação Falhou", e.getMessage());
        } catch (Exception e) {
            // Captura erros de persistência (ex: nome duplicado)
            exibirAlerta(AlertType.ERROR, "Erro de Persistência", "Erro ao salvar: " + e.getMessage());
        }
    }
    
    // Método privado para carregar dados do banco e atualizar a ListView
    private void carregarCategorias() {
        List<Categoria> listaCategorias = categoriaService.findAll();
        
        // Converte a lista de objetos Categoria para uma lista de Strings para exibir
        categoriasObservableList.clear();
        for (Categoria c : listaCategorias) {
            categoriasObservableList.add(c.getId() + " - " + c.getNome());
        }
    }

    // Utilitário para exibir alertas
    private void exibirAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}