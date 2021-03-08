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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="time_zone", indexes={@Index(name="time_zone_zone_id_IX", columnList="zone_id", unique=true)})
public class TimeZone implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8187301339430316026L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="zone_id", unique=true, nullable=false, length=55)
    private String zoneId;
    @OneToMany(mappedBy="timeZone")
    private Set<Airport> airport;

    /** Default constructor. */
    public TimeZone() {
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
     * Access method for zoneId.
     *
     * @return the current value of zoneId
     */
    public String getZoneId() {
        return zoneId;
    }

    /**
     * Setter method for zoneId.
     *
     * @param aZoneId the new value for zoneId
     */
    public void setZoneId(String aZoneId) {
        zoneId = aZoneId;
    }

    /**
     * Access method for airport.
     *
     * @return the current value of airport
     */
    public Set<Airport> getAirport() {
        return airport;
    }

    /**
     * Setter method for airport.
     *
     * @param aAirport the new value for airport
     */
    public void setAirport(Set<Airport> aAirport) {
        airport = aAirport;
    }

    /**
     * Compares the key for this instance with another TimeZone.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TimeZone and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TimeZone)) {
            return false;
        }
        TimeZone that = (TimeZone) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TimeZone.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TimeZone)) return false;
        return this.equalKeys(other) && ((TimeZone)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[TimeZone |");
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
