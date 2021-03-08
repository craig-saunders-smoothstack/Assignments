// Generated with g9.

package com.ss.craig.week.two.weekend.assignment.jpaentities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="airplane")
public class Airplane implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 350468337066624339L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @ManyToOne(optional=false)
    @JoinColumn(name="type_id", nullable=false)
    private AirplaneType airplaneType;
    @OneToMany(mappedBy="airplane")
    private Set<Flight> flight;

    /** Default constructor. */
    public Airplane() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for airplaneType.
     *
     * @return the current value of airplaneType
     */
    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    /**
     * Setter method for airplaneType.
     *
     * @param aAirplaneType the new value for airplaneType
     */
    public void setAirplaneType(AirplaneType aAirplaneType) {
        airplaneType = aAirplaneType;
    }

    /**
     * Access method for flight.
     *
     * @return the current value of flight
     */
    public Set<Flight> getFlight() {
        return flight;
    }

    /**
     * Setter method for flight.
     *
     * @param aFlight the new value for flight
     */
    public void setFlight(Set<Flight> aFlight) {
        flight = aFlight;
    }

    /**
     * Compares the key for this instance with another Airplane.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Airplane and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Airplane)) {
            return false;
        }
        Airplane that = (Airplane) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Airplane.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Airplane)) return false;
        return this.equalKeys(other) && ((Airplane)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getId();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Airplane |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
