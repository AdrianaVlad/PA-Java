package entities;

import com.mycompany.elevatorsimulator.EntityInterface;
import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Elevators.findAll", query = "SELECT e FROM Elevators e"),
    @NamedQuery(name = "Elevators.findNextId", query = "SELECT MAX(e.id)+1 FROM Elevators e"),
    @NamedQuery(name = "Elevators.findById", query = "SELECT e FROM Elevators e WHERE e.id = ?1"),
    @NamedQuery(name = "Elevators.findCanReachFloor", query = "SELECT e FROM Elevators e WHERE e.status = 'online' AND e.lowestFloor <= ?1 AND e.highestFloor >= ?1 AND e.buildingId = ?2")})
public class Elevators implements Serializable,EntityInterface  {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "LOWEST_FLOOR")
    private BigInteger lowestFloor;
    @Basic(optional = false)
    @Column(name = "HIGHEST_FLOOR")
    private BigInteger highestFloor;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "CURRENT_FLOOR")
    private BigInteger currentFloor;
    @Column(name = "MAX_PEOPLE")
    private BigInteger maxPeople;
    @Column(name = "MAX_WEIGHT")
    private BigInteger maxWeight;
    @JoinColumn(name = "BUILDING_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Buildings buildingId;

    public Elevators() {
    }

    public Elevators(Integer id) {
        this.id = id;
    }

    public Elevators(Integer id, BigInteger lowestFloor, BigInteger highestFloor, String status, BigInteger currentFloor) {
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

    public BigInteger getLowestFloor() {
        return lowestFloor;
    }

    public void setLowestFloor(BigInteger lowestFloor) {
        this.lowestFloor = lowestFloor;
    }

    public BigInteger getHighestFloor() {
        return highestFloor;
    }

    public void setHighestFloor(BigInteger highestFloor) {
        this.highestFloor = highestFloor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(BigInteger currentFloor) {
        this.currentFloor = currentFloor;
    }

    public BigInteger getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(BigInteger maxPeople) {
        this.maxPeople = maxPeople;
    }

    public BigInteger getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(BigInteger maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Buildings getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Buildings buildingId) {
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
        if (!(object instanceof Elevators)) {
            return false;
        }
        Elevators other = (Elevators) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Elevators[ id=" + id + " ]";
    }
    
}
