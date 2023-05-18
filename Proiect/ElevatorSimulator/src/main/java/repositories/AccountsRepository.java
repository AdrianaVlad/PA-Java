package repositories;

import com.mycompany.elevatorsimulator.AbstractRepository;
import entities.Accounts;
import java.sql.SQLException;


/**
 *
 * @author Vlad Adriana
 */
public class AccountsRepository extends AbstractRepository<Accounts>{
    public AccountsRepository(){
        this.name="Accounts";
    }
    public Accounts findByName(String name) throws SQLException{
        return (Accounts)em.createNamedQuery("Accounts.findByName").setParameter(1,name).getSingleResult();
    }
}
