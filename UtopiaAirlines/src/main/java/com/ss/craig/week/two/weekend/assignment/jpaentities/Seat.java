// Generated with g9.

package com.ss.craig.week.two.weekend.assignment.jpaentities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="seat")
public class Seat implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5285035957111133066L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="seat_number", nullable=false, precision=10)
    private int seatNumber;
    @ManyToOne(optional=false)
    @JoinColumn(name="airplane_id", nullable=false)
    private Airplane airplane;

    /** Default constructor. */
    public Seat() {
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
     * Access method for seatNumber.
     *
     * @return the current value of seatNumber
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Setter method for seatNumber.
     *
     * @param aSeatNumber the new value for seatNumber
     */
    public void setSeatNumber(int aSeatNumber) {
        seatNumber = aSeatNumber;
    }

    /**
     * Access method for airplane.
     *
     * @return the current value of airplane
     */
    public Airplane getAirplane() {
        return airplane;
    }

    /**
     * Setter method for airplane.
     *
     * @param aAirplane the new value for airplane
     */
    public void setAirplane(Airplane aAirplane) {
        airplane = aAirplane;
    }

    /**
     * Compares the key for this instance with another Seat.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Seat and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Seat)) {
            return false;
        }
        Seat that = (Seat) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Seat.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Seat)) return false;
        return this.equalKeys(other) && ((Seat)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Seat |");
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
