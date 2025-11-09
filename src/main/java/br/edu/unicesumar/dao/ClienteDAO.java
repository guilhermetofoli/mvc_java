package br.edu.unicesumar.dao;

import br.edu.unicesumar.model.Cliente;
import jakarta.persistence.TypedQuery;

// Herda as operações CRUD para a Entidade Cliente
public class ClienteDAO extends DAO<Cliente> {

    public ClienteDAO() {
        super();
    }

    // Exemplo de método específico para o Cliente
    public Cliente findByCpf(String cpf) {
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}