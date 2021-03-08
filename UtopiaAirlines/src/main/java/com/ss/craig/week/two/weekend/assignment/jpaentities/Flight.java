// Generated with g9.

package com.ss.craig.week.two.weekend.assignment.jpaentities;

import java.io.Serializable;
import java.time.LocalDateTime;
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

@Entity(name="flight")
public class Flight implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6877913391086851091L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="departure_time", nullable=false)
    private LocalDateTime departureTime;
    @Column(name="reserved_seats", nullable=false, precision=10)
    private int reservedSeats;
    @Column(name="seat_price", nullable=false, precision=12)
    private float seatPrice;
    @OneToMany(mappedBy="flight")
    private Set<FlightBookings> flightBookings;
    @ManyToOne(optional=false)
    @JoinColumn(name="airplane_id", nullable=false)
    private Airplane airplane;
    @ManyToOne(optional=false)
    @JoinColumn(name="route_id", nullable=false)
    private Route route;

    /** Default constructor. */
    public Flight() {
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
     * Access method for departureTime.
     *
     * @return the current value of departureTime
     */
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Setter method for departureTime.
     *
     * @param aDepartureTime the new value for departureTime
     */
    public void setDepartureTime(LocalDateTime aDepartureTime) {
        departureTime = aDepartureTime;
    }

    /**
     * Access method for reservedSeats.
     *
     * @return the current value of reservedSeats
     */
    public int getReservedSeats() {
        return reservedSeats;
    }

    /**
     * Setter method for reservedSeats.
     *
     * @param aReservedSeats the new value for reservedSeats
     */
    public void setReservedSeats(int aReservedSeats) {
        reservedSeats = aReservedSeats;
    }

    /**
     * Access method for seatPrice.
     *
     * @return the current value of seatPrice
     */
    public float getSeatPrice() {
        return seatPrice;
    }

    /**
     * Setter method for seatPrice.
     *
     * @param aSeatPrice the new value for seatPrice
     */
    public void setSeatPrice(float aSeatPrice) {
        seatPrice = aSeatPrice;
    }

    /**
     * Access method for flightBookings.
     *
     * @return the current value of flightBookings
     */
    public Set<FlightBookings> getFlightBookings() {
        return flightBookings;
    }

    /**
     * Setter method for flightBookings.
     *
     * @param aFlightBookings the new value for flightBookings
     */
    public void setFlightBookings(Set<FlightBookings> aFlightBookings) {
        flightBookings = aFlightBookings;
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
     * Access method for route.
     *
     * @return the current value of route
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Setter method for route.
     *
     * @param aRoute the new value for route
     */
    public void setRoute(Route aRoute) {
        route = aRoute;
    }

    /**
     * Compares the key for this instance with another Flight.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Flight and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Flight)) {
            return false;
        }
        Flight that = (Flight) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Flight.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Flight)) return false;
        return this.equalKeys(other) && ((Flight)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Flight |");
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
