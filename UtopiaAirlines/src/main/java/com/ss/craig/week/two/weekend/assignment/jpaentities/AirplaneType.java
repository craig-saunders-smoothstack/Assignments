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
import javax.persistence.OneToMany;

@Entity(name="airplane_type")
public class AirplaneType implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4842652399010718526L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="max_capacity", nullable=false, precision=10)
    private int maxCapacity;
    @OneToMany(mappedBy="airplaneType")
    private Set<Airplane> airplane;

    /** Default constructor. */
    public AirplaneType() {
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
     * Access method for maxCapacity.
     *
     * @return the current value of maxCapacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Setter method for maxCapacity.
     *
     * @param aMaxCapacity the new value for maxCapacity
     */
    public void setMaxCapacity(int aMaxCapacity) {
        maxCapacity = aMaxCapacity;
    }

    /**
     * Access method for airplane.
     *
     * @return the current value of airplane
     */
    public Set<Airplane> getAirplane() {
        return airplane;
    }

    /**
     * Setter method for airplane.
     *
     * @param aAirplane the new value for airplane
     */
    public void setAirplane(Set<Airplane> aAirplane) {
        airplane = aAirplane;
    }

    /**
     * Compares the key for this instance with another AirplaneType.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class AirplaneType and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof AirplaneType)) {
            return false;
        }
        AirplaneType that = (AirplaneType) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another AirplaneType.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AirplaneType)) return false;
        return this.equalKeys(other) && ((AirplaneType)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[AirplaneType |");
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
