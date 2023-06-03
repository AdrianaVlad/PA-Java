package repositories;

import com.mycompany.elevatorsimulator.EntityManagerFactoryManager;
import entities.EntityInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Vlad Adriana
 * @param <T>
 */
public abstract class AbstractRepository <T extends EntityInterface>{
    public EntityManagerFactory emf = EntityManagerFactoryManager.getEntityManagerFactory();
    public EntityManager em = emf.createEntityManager();
    public String name;
    public void insert(T line) throws SQLException{
        try{
            em.getTransaction().begin();
            em.persist(line);
            em.getTransaction().commit();
        } catch (Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }
    }
    public void delete(T line) throws SQLException{
        try{
            em.getTransaction().begin();
            line = em.merge(line);
            em.remove(line);
            em.getTransaction().commit();
        } catch (Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }
    }
    public List<T> findAll() throws SQLException{
        return (List<T>)em.createNamedQuery(name+".findAll").getResultList();
    }
    public T findById(int id) throws SQLException{
        return (T)em.createNamedQuery(name+".findById").setParameter(1,id).getSingleResult();
    }
    public int findNextId() throws SQLException{
        return Optional.ofNullable(em.createNamedQuery(name+".findNextId",Integer.class).getSingleResult()).orElse(1);
    }
}
