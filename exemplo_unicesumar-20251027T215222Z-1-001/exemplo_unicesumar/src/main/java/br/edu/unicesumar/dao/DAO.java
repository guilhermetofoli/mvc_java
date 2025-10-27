package br.edu.unicesumar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAO<Object> {

    protected EntityManager em;
    //PADRÃO SINGLETON
    private static DAO instance;

    protected DAO(){
        em = getEntityManager();
    }

    private EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("exemplo_unicesumar");
        if(em == null){
            //PADRÃO FACTORY METHOD
            em = emf.createEntityManager();
        }
        return em;
    }

    public static DAO getInstance(){
        if(instance == null){
            instance = new DAO();
        }
        return instance;
    }

    //INSERÇÃO
    public void save(Object object){
        try {
            //INICIANDO UMA TRANSAÇÃO
            em.getTransaction().begin();
            //SALVAR O OBJETO
            em.persist(object);
            //FECHAR A TRANSAÇÃO
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            //FAZENDO ROLLBACK PARA NÃO SALVAR A INFORMAÇÃO NO BANCO DE DADOS
            em.getTransaction().rollback();
        }
    }

    //ATUALIZAÇÃO
    public void update(Object object){
        try {
            //INICIANDO UMA TRANSAÇÃO
            em.getTransaction().begin();
            //SALVAR O OBJETO
            em.merge(object);
            //FECHAR A TRANSAÇÃO
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            //FAZENDO ROLLBACK PARA NÃO SALVAR A INFORMAÇÃO NO BANCO DE DADOS
            em.getTransaction().rollback();
        }
    }

    //DELETE
    public void delete(Object object){
        try {
            //INICIANDO UMA TRANSAÇÃO
            em.getTransaction().begin();
            //SALVAR O OBJETO
            em.remove(object);
            //FECHAR A TRANSAÇÃO
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            //FAZENDO ROLLBACK PARA NÃO SALVAR A INFORMAÇÃO NO BANCO DE DADOS
            em.getTransaction().rollback();
        }
    }
    
}
