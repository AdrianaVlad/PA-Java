package repositories;

import entities.Building;
import entities.Elevator;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class ElevatorRepository extends AbstractRepository<Elevator> {
    public ElevatorRepository(){
        this.name="Elevator";
        refresh();
    }
     public List<Elevator> findCanReachFloor(int floor, Building building) throws SQLException{
        return (List<Elevator>)em.createNamedQuery("Elevator.findCanReachFloor").setParameter(1,floor).setParameter(2,building).getResultList();
    }
    public void update(Elevator elevator, String status, int lowestFloor, int highestFloor, int currentFloor){
        em.getTransaction().begin();
        elevator.setLowestFloor(lowestFloor);
        elevator.setHighestFloor(highestFloor);
        elevator.setCurrentFloor(currentFloor);
        elevator.setStatus(status);
        em.merge(elevator);
        em.getTransaction().commit();
    }
    public void updateFloorById(int id, int floor) throws SQLException{
        Elevator elevator = findById(id);
        em.getTransaction().begin();
        elevator.setCurrentFloor(floor);
        em.merge(elevator);
        em.getTransaction().commit();
    }
    public void updateStatusById(int id, String status) throws SQLException{
        Elevator elevator = findById(id);
        em.getTransaction().begin();
        elevator.setStatus(status);
        em.merge(elevator);
        em.getTransaction().commit();
    }
    public final void refresh(){
        try {
            List<Elevator> elevatorList = findAll();
            for(Elevator elevator:elevatorList){
                em.refresh(elevator);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void refresh(int id){
        try {
            em.refresh(findById(id));
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
