package br.edu.unicesumar.controller;

import br.edu.unicesumar.model.Cliente;
import br.edu.unicesumar.model.Endereco;
import br.edu.unicesumar.service.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClienteController {

    // Componentes de Interface para coleta de dados
    @FXML
    private TextField txtNome; 
    
    @FXML
    private TextField txtCpf;
    
    @FXML
    private TextField txtEmail;

    // Você precisaria de TextFields para os campos do Endereco também!
    
    @FXML
    private Label lblStatus; 

    // Instancia o Service
    private ClienteService clienteService = new ClienteService();

    @FXML
    public void handleSalvarCliente() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String email = txtEmail.getText();

            Endereco endereco = new Endereco(0, "Rua do Controller", "123", "Bairro X", "Cidade Y", "ST", "00000-000"); 

            Cliente novoCliente = new Cliente(0, nome, cpf, email, endereco); 
            clienteService.save(novoCliente);

            lblStatus.setText("Cliente " + nome + " cadastrado com sucesso!");
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Concluído");
            alert.setContentText("Novo cliente salvo. (Endereço é @Transient e não foi persistido!)");
            alert.showAndWait();

        } catch (IllegalArgumentException e) {
            
             lblStatus.setText("ERRO: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            lblStatus.setText("ERRO de Persistência.");
        }
    }
}