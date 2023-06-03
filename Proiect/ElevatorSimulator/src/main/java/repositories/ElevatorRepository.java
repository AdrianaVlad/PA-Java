package repositories;

import entities.Building;
import entities.Elevator;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    public Elevator findByCode(int code) throws SQLException{
        return (Elevator)em.createNamedQuery("Elevator.findByCode").setParameter(1,code).getSingleResult();
    }
    public int findNextCode(){
        return Optional.ofNullable(em.createNamedQuery(name+".findNextCode",Integer.class).getSingleResult()).orElse(1);
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
    public void updateFloorByCode(int code, int floor) throws SQLException{
        Elevator elevator = findByCode(code);
        em.getTransaction().begin();
        elevator.setCurrentFloor(floor);
        em.merge(elevator);
        em.getTransaction().commit();
    }
    public void updateStatusByCode(int code, String status) throws SQLException{
        Elevator elevator = findByCode(code);
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
    public void refresh(int code){
        try {
            em.refresh(findByCode(code));
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
