package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.EnderecoDAO;
import br.edu.unicesumar.model.Endereco;

public class EnderecoService {
    
    private EnderecoDAO enderecoDAO = new EnderecoDAO(); 

    public void save(Endereco endereco) {
        // Regra de Negócio: Aqui é onde você faria validações específicas de endereço.
        if (endereco.getCep() == null || endereco.getCep().length() != 9) { // Assumindo formato 00000-000
            throw new IllegalArgumentException("CEP deve ter 8 dígitos.");
        }
        
        // Delega a persistência
        enderecoDAO.save(endereco); 
    }
    
    public Endereco findById(int id) {
        // Como o DAO genérico não tem um findById padrão no código que você me mostrou,
        // este método dependeria de adicionar um findById no seu DAO genérico ou implementar a HQL.
        // Por enquanto, apenas para compilação:
        return null; 
    }
    
    // Você adicionaria outros métodos findById, findByCep, etc.
}