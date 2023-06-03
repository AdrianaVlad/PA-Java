package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Vlad Adriana
 */
@Entity
@Table(name = "ELEVATORS")
@NamedQueries({
    @NamedQuery(name = "Elevator.findAll", query = "SELECT e FROM Elevator e"),
    @NamedQuery(name = "Elevator.findNextId", query = "SELECT MAX(e.id)+1 FROM Elevator e"),
    @NamedQuery(name = "Elevator.findById", query = "SELECT e FROM Elevator e WHERE e.id = ?1"),
    @NamedQuery(name = "Elevator.findCanReachFloor", query = "SELECT e FROM Elevator e WHERE e.lowestFloor <= ?1 AND e.highestFloor >= ?1 AND e.buildingId = ?2")})
public class Elevator implements Serializable,EntityInterface  {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "LOWEST_FLOOR")
    private Integer lowestFloor;
    @Basic(optional = false)
    @Column(name = "HIGHEST_FLOOR")
    private Integer highestFloor;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "CURRENT_FLOOR")
    private Integer currentFloor;
    @JoinColumn(name = "BUILDING_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Building buildingId;
    public Elevator(){
        
    }
    public Elevator(Integer id, Integer lowestFloor, Integer highestFloor, String status, Integer currentFloor, Building buildingId) {
        this.id = id;
        this.lowestFloor = lowestFloor;
        this.highestFloor = highestFloor;
        this.status = status;
        this.currentFloor = currentFloor;
        this.buildingId = buildingId;
    }


    
    public Elevator(Integer id, Integer lowestFloor, Integer highestFloor, String status, Integer currentFloor) {
        this.id = id;
        this.lowestFloor = lowestFloor;
        this.highestFloor = highestFloor;
        this.status = status;
        this.currentFloor = currentFloor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLowestFloor() {
        return lowestFloor;
    }

    public void setLowestFloor(Integer lowestFloor) {
        this.lowestFloor = lowestFloor;
    }

    public Integer getHighestFloor() {
        return highestFloor;
    }

    public void setHighestFloor(Integer highestFloor) {
        this.highestFloor = highestFloor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }
    @JsonIgnore
    public Building getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Building buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Elevator)) {
            return false;
        }
        Elevator other = (Elevator) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Elevators[ id=" + id + " ]";
    }
    
}
