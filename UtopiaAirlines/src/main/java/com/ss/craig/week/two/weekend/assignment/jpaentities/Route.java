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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="route", indexes={@Index(name="routeUniqueRoute", columnList="origin_id,destination_id", unique=true)})
public class Route implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -374633073689508664L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @ManyToOne(optional=false)
    @JoinColumn(name="origin_id", nullable=false)
    private Airport airport;
    @ManyToOne(optional=false)
    @JoinColumn(name="destination_id", nullable=false)
    private Airport airport2;
    @OneToMany(mappedBy="route")
    private Set<Flight> flight;

    /** Default constructor. */
    public Route() {
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
     * Access method for airport.
     *
     * @return the current value of airport
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * Setter method for airport.
     *
     * @param aAirport the new value for airport
     */
    public void setAirport(Airport aAirport) {
        airport = aAirport;
    }

    /**
     * Access method for airport2.
     *
     * @return the current value of airport2
     */
    public Airport getAirport2() {
        return airport2;
    }

    /**
     * Setter method for airport2.
     *
     * @param aAirport2 the new value for airport2
     */
    public void setAirport2(Airport aAirport2) {
        airport2 = aAirport2;
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
     * Gets the group fragment iataId for member airport.
     *
     * @return Current value of the group fragment
     * @see Airport
     */
    public String getAirportIataId() {
        return (getAirport() == null ? null : getAirport().getIataId());
    }

    /**
     * Sets the group fragment iataId from member airport.
     *
     * @param aIataId New value for the group fragment
     * @see Airport
     */
    public void setAirportIataId(String aIataId) {
        if (getAirport() != null) {
            getAirport().setIataId(aIataId);
        }
    }

    /**
     * Gets the group fragment iataId for member airport2.
     *
     * @return Current value of the group fragment
     * @see Airport
     */
    public String getAirport2IataId() {
        return (getAirport2() == null ? null : getAirport2().getIataId());
    }

    /**
     * Sets the group fragment iataId from member airport2.
     *
     * @param aIataId New value for the group fragment
     * @see Airport
     */
    public void setAirport2IataId(String aIataId) {
        if (getAirport2() != null) {
            getAirport2().setIataId(aIataId);
        }
    }

    /**
     * Compares the key for this instance with another Route.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Route and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Route)) {
            return false;
        }
        Route that = (Route) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Route.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Route)) return false;
        return this.equalKeys(other) && ((Route)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Route |");
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
