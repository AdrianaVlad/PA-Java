package repositories;

import com.mycompany.elevatorsimulator.AbstractRepository;
import entities.Elevators;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class ElevatorsRepository extends AbstractRepository<Elevators> {
    public ElevatorsRepository(){
        this.name="Elevators";
    }
    public List<Elevators> findCanReachFloor(String name) throws SQLException{
        return (List<Elevators>)em.createNamedQuery("Elevators.findCanReachFloor").setParameter(1,name).getResultList();
    }
}
