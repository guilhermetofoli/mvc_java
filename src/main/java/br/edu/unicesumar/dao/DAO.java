package br.edu.unicesumar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

// CORRIGIDO: Usa <T> (Type) para o tipo genérico
public class DAO<T> { 

    protected EntityManager em;
    // PADRÃO SINGLETON
    private static DAO<?> instance; 

    protected DAO(){
        em = getEntityManager();
    }

    private EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("exemplo_unicesumar");
        if(em == null){
            em = emf.createEntityManager();
        }
        return em;
    }

    // CORRIGIDO: Retorna o tipo genérico
    public static DAO getInstance(){
        if(instance == null){
            instance = new DAO<>();
        }
        return instance;
    }

    // INSERÇÃO
    public void save(T entity){
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    // ATUALIZAÇÃO
    public void update(T entity){
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    // DELETE
    public void delete(T entity){
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    
    // NOVO MÉTODO: Essencial para listagens (usado em CategoriaService)
    public List<T> findAll(Class<T> clazz) {
        String className = clazz.getSimpleName();
        TypedQuery<T> query = em.createQuery("SELECT c FROM " + className + " c", clazz);
        return query.getResultList();
    }
    
    // NOVO MÉTODO: Essencial para buscar por ID
    public T findById(Class<T> clazz, Integer id) {
        return em.find(clazz, id);
    }
}