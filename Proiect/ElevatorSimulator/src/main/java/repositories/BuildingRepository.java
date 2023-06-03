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
public class BuildingRepository extends AbstractRepository<Building>{
    public BuildingRepository(){
        this.name="Building";
        refresh();
    }
    public Building findByName(String name) throws SQLException{
        return (Building)em.createNamedQuery("Building.findByName").setParameter(1,name).getSingleResult();
    }
    public void addRightsById(int buildingId, Account account) throws SQLException{
        Building building = findById(buildingId);
        em.getTransaction().begin();
        building.getAccountsList().add(account);
        em.merge(account);
        em.getTransaction().commit();
    }
    public void removeRightsById(int buildingId, Account account) throws SQLException{
        Building building = findById(buildingId);
        em.getTransaction().begin();
        building.getAccountsList().remove(account);
        em.merge(account);
        em.getTransaction().commit();
    }
    public final void refresh(){
        try {
            List<Building> buildingsList = findAll();
            for(Building building : buildingsList){
                em.refresh(building);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuildingRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
