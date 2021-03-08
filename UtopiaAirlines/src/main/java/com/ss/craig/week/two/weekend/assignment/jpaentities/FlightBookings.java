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

@Entity(name="flight_bookings")
public class FlightBookings implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -387730438583180614L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @ManyToOne(optional=false)
    @JoinColumn(name="booking_id", nullable=false)
    private Booking booking;
    @ManyToOne(optional=false)
    @JoinColumn(name="flight_id", nullable=false)
    private Flight flight;

    /** Default constructor. */
    public FlightBookings() {
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
     * Access method for booking.
     *
     * @return the current value of booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * Setter method for booking.
     *
     * @param aBooking the new value for booking
     */
    public void setBooking(Booking aBooking) {
        booking = aBooking;
    }

    /**
     * Access method for flight.
     *
     * @return the current value of flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Setter method for flight.
     *
     * @param aFlight the new value for flight
     */
    public void setFlight(Flight aFlight) {
        flight = aFlight;
    }

    /**
     * Compares the key for this instance with another FlightBookings.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class FlightBookings and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof FlightBookings)) {
            return false;
        }
        FlightBookings that = (FlightBookings) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another FlightBookings.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FlightBookings)) return false;
        return this.equalKeys(other) && ((FlightBookings)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[FlightBookings |");
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
