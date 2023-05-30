package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vlad Adriana
 */
@Entity
@Table(name = "BUILDINGS")
@NamedQueries({
    @NamedQuery(name = "Buildings.findAll", query = "SELECT b FROM Buildings b"),
    @NamedQuery(name = "Buildings.findNextId", query = "SELECT MAX(b.id)+1 FROM Buildings b"),
    @NamedQuery(name = "Buildings.findById", query = "SELECT b FROM Buildings b WHERE b.id = ?1"),
    @NamedQuery(name = "Buildings.findByName", query = "SELECT b FROM Buildings b WHERE b.name = ?1")})
public class Buildings implements Serializable,EntityInterface  {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @JoinTable(name = "ADMIN_RIGHTS", joinColumns = {
        @JoinColumn(name = "BUILDING_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Accounts> accountsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingId")
    private List<Elevators> elevatorsList;
    public Buildings(){
        
    }
    public Buildings(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonIgnore
    public List<Accounts> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Accounts> accountsList) {
        this.accountsList = accountsList;
    }
    @JsonIgnore
    public List<Elevators> getElevatorsList() {
        return elevatorsList;
    }

    public void setElevatorsList(List<Elevators> elevatorsList) {
        this.elevatorsList = elevatorsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Buildings)) {
            return false;
        }
        Buildings other = (Buildings) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Buildings[ id=" + id + " ]";
    }
    
}
