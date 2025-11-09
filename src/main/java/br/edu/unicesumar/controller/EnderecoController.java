package br.edu.unicesumar.controller;

import br.edu.unicesumar.model.Endereco;
import br.edu.unicesumar.service.EnderecoService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EnderecoController {

    // Componentes de Interface FXML para coleta de dados do Endereco
    @FXML private TextField txtLogradouro;
    @FXML private TextField txtNumero;
    @FXML private TextField txtBairro;
    @FXML private TextField txtCidade;
    @FXML private TextField txtEstado;
    @FXML private TextField txtCep;
    
    @FXML private Label lblStatus; 

    // Instancia o Service
    private EnderecoService enderecoService = new EnderecoService();

    // Método disparado pelo botão 'Salvar' (ex: usado para adicionar um novo endereço)
    @FXML
    public void handleSalvarEndereco() {
        try {
            // 1. Coleta dados da View
            String logradouro = txtLogradouro.getText();
            String numero = txtNumero.getText();
            String bairro = txtBairro.getText();
            String cidade = txtCidade.getText();
            String estado = txtEstado.getText();
            String cep = txtCep.getText();

            // 2. Cria o objeto Endereco
            // O construtor de Endereco é: (int id, String logradouro, String numero, String bairro, String cidade, String estado, String cep)
            Endereco novoEndereco = new Endereco(0, logradouro, numero, bairro, cidade, estado, cep); 

            // 3. Chama o Service (que valida e persiste)
            enderecoService.save(novoEndereco);

            // 4. Feedback e Limpeza
            exibirAlerta(AlertType.INFORMATION, "Sucesso", "Endereço salvo com ID: " + novoEndereco.getId());
            limparCampos();

        } catch (IllegalArgumentException e) {
            // Captura erros de validação do Service
            exibirAlerta(AlertType.WARNING, "Validação Falhou", e.getMessage());
        } catch (Exception e) {
            // Captura erros de persistência
            exibirAlerta(AlertType.ERROR, "Erro de Persistência", "Falha ao salvar endereço: " + e.getMessage());
        }
    }
    
    // Método para ser chamado por outros controllers para editar um endereço
    public void carregarEnderecoParaEdicao(int idEndereco) {
        try {
            Endereco endereco = enderecoService.findById(idEndereco);
            if (endereco != null) {
                txtLogradouro.setText(endereco.getLogradouro());
                txtNumero.setText(endereco.getNumero());
                txtBairro.setText(endereco.getBairro());
                txtCidade.setText(endereco.getCidade());
                txtEstado.setText(endereco.getEstado());
                txtCep.setText(endereco.getCep());
            }
        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro", "Não foi possível carregar o endereço.");
        }
    }
    
    private void limparCampos() {
        txtLogradouro.clear();
        txtNumero.clear();
        txtBairro.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtCep.clear();
        lblStatus.setText("Campos limpos.");
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