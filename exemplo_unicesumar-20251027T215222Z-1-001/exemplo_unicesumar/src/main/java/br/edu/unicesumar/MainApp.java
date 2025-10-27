package br.edu.unicesumar;

import java.io.IOException;

import br.edu.unicesumar.dao.CategoriaDAO;
import br.edu.unicesumar.dao.ClienteDAO;
import br.edu.unicesumar.dao.PedidoDAO;
import br.edu.unicesumar.dao.ProdutoDAO;
import br.edu.unicesumar.dao.UsuarioDAO;
import br.edu.unicesumar.model.Categoria;
import br.edu.unicesumar.model.Cliente;
import br.edu.unicesumar.model.Endereco;
import br.edu.unicesumar.model.ItemPedido;
import br.edu.unicesumar.model.Pedido;
import br.edu.unicesumar.model.Produto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    private static Stage stage;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        stage=s;
        
        testarPersistenciaCompleta();

        setRoot("primary","Sistema E-Commerce");
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void testarPersistenciaCompleta() {
        System.out.println("--- INICIANDO TESTE DE PERSISTÊNCIA ---");
        
        // Categoria
        Categoria c1 = new Categoria("Eletrônicos");
        categoriaDAO.save(c1);

        // Produto  
        Produto p1 = new Produto("Smartphone X", "Top de linha", 1999.99, c1);
        produtoDAO.save(p1);
        
        // Cliente com Endereco
        // Endereco exige 7 argumentos (id, logradouro, ...)
        Endereco end1 = new Endereco(0, "Rua das Flores", "55", "Zona 7", "Maringá", "PR", "87020-000");
        
        // Cliente exige 5 argumentos por posição (id, nome, cpf, email, endereco)
        Cliente cliente1 = new Cliente(0, "Ana Paula", "12345678900", "ana.paula@teste.com", end1); 
        clienteDAO.save(cliente1);

        // Pedido e ItemPedido
        Pedido pedido1 = new Pedido();
        pedido1.setCliente(cliente1);
        
        ItemPedido item1 = new ItemPedido(p1, 1);
        pedido1.addItem(item1);

        pedido1.setValorTotal(p1.getPreco() * item1.getQuantidade());

        pedidoDAO.save(pedido1);

        System.out.println("--- TESTE DE PERSISTÊNCIA CONCLUÍDO ---");
    }

    public static void main(String[] args) {
        launch(args);
    }
}