package br.edu.unicesumar.dao;

import java.util.List;

import br.edu.unicesumar.model.Usuario;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO extends DAO<Usuario>{

    //LISTANDO TODOS OS USUÁRIOS
    public List<Usuario> listAllUsers(){
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", 
                Usuario.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    //LISTANDO O USUÁRIO PELO IDENTIFICADOR
    public Usuario findUserById(int id){
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u " +
                    "WHERE u.id = :id", Usuario.class);
            query.setParameter("id", id);
            return query.getSingleResult();      
        } catch (Exception e) {
            return null;
        }
    }
    
}
