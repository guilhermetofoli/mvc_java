package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.ClienteDAO;
import br.edu.unicesumar.model.Cliente;
import java.util.List;

public class ClienteService {

    // Declaração correta do campo (private é bom)
    private ClienteDAO clienteDAO = new ClienteDAO(); 

    public void save(Cliente cliente) {
        // Regra de Negócio: Se precisar validar o CPF, seria aqui.
        // if (!isValid(cliente.getCpf())) { throw new RuntimeException("CPF inválido"); }
        
        // Chama o método SAVE do DAO
        clienteDAO.save(cliente); 
    }
    
    // MÉTODO ESSENCIAL 1: Retorna a lista de clientes para o ComboBox
    public List<Cliente> findAll() {
        // Assume que seu DAO genérico (DAO.java) tem o método findAll(Class<T> clazz)
        return clienteDAO.findAll(Cliente.class);
    }
    
    // MÉTODO ESSENCIAL 2: Busca por nome para o PedidoController
    public Cliente findByName(String nome) {
        // Na prática, isso chamaria um método customizado no ClienteDAO
        // Por exemplo: clienteDAO.findByName(nome);
        
        // Para fins de teste (Busca simples em memória ou chamada de método customizado):
        List<Cliente> clientes = this.findAll();
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }
    
    // Adicione outros métodos findById, etc.
    public Cliente findById(int id) {
        return clienteDAO.findById(Cliente.class, id);
    }
}