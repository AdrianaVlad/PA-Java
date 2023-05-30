package repositories;

import entities.Accounts;
import entities.Buildings;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Vlad Adriana
 */
public class AccountsRepository extends AbstractRepository<Accounts>{
    public AccountsRepository(){
        this.name="Accounts";
        try {
            List<Accounts> accounts = findAll();
            for(Accounts account: accounts){
                em.refresh(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Accounts findByName(String name) throws SQLException{
        return (Accounts)em.createNamedQuery("Accounts.findByName").setParameter(1,name).getSingleResult();
    }
    public void updateNameById(int id, String name) throws SQLException{
        Accounts account = findById(id);
        em.getTransaction().begin();
        account.setName(name);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void updatePasswordById(int id, String password) throws SQLException{
        Accounts account = findById(id);
        em.getTransaction().begin();
        account.setPassword(password);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void addRightsById(int accountId, Buildings building) throws SQLException{
        Accounts account = findById(accountId);
        em.getTransaction().begin();
        account.getBuildingsList().add(building);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void removeRightsById(int accountId, Buildings building) throws SQLException{
        Accounts account = findById(accountId);
        em.getTransaction().begin();
        account.getBuildingsList().remove(building);
        em.merge(account);
        em.getTransaction().commit();
    }
}
