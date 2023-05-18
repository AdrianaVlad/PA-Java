package repositories;

import com.mycompany.elevatorsimulator.AbstractRepository;
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
}
