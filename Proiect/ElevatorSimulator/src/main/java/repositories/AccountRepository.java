package repositories;

import entities.Account;
import entities.Building;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Vlad Adriana
 */
public class AccountRepository extends AbstractRepository<Account>{
    public AccountRepository(){
        this.name="Account";
        refresh();
    }
    public Account findByName(String name) throws SQLException{
        return (Account)em.createNamedQuery("Account.findByName").setParameter(1,name).getSingleResult();
    }
    public void updateNameById(int id, String name) throws SQLException{
        Account account = findById(id);
        em.getTransaction().begin();
        account.setName(name);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void updatePasswordById(int id, String password) throws SQLException{
        Account account = findById(id);
        em.getTransaction().begin();
        account.setPassword(password);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void addRightsById(int accountId, Building building) throws SQLException{
        Account account = findById(accountId);
        em.getTransaction().begin();
        account.getBuildingsList().add(building);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void removeRightsById(int accountId, Building building) throws SQLException{
        Account account = findById(accountId);
        em.getTransaction().begin();
        account.getBuildingsList().remove(building);
        em.merge(account);
        em.getTransaction().commit();
    }
    public final void refresh(){
        try {
            List<Account> accounts = findAll();
            for(Account account: accounts){
                em.refresh(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
