package repositories;

import entities.Accounts;
import entities.Buildings;
import java.sql.SQLException;

/**
 *
 * @author Vlad Adriana
 */
public class BuildingsRepository extends AbstractRepository<Buildings>{
    public BuildingsRepository(){
        this.name="Buildings";
    }
    public Buildings findByName(String name) throws SQLException{
        return (Buildings)em.createNamedQuery("Buildings.findByName").setParameter(1,name).getSingleResult();
    }
    public void addRightsById(int buildingId, Accounts account) throws SQLException{
        Buildings building = findById(buildingId);
        em.getTransaction().begin();
        building.getAccountsList().add(account);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void removeRightsById(int buildingId, Accounts account) throws SQLException{
        Buildings building = findById(buildingId);
        em.getTransaction().begin();
        building.getAccountsList().remove(account);
        em.merge(account);
        em.getTransaction().commit();
    }
}
