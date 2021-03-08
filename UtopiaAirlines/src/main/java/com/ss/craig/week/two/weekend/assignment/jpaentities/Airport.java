// Generated with g9.

package com.ss.craig.week.two.weekend.assignment.jpaentities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="airport")
public class Airport implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5007971625896421477L;

    /** Primary key. */
    protected static final String PK = "iataId";

    @Id
    @Column(name="iata_id", unique=true, nullable=false, length=3)
    private String iataId;
    @Column(nullable=false, length=64)
    private String name;
    @Column(name="iso_region", nullable=false, length=5)
    private String isoRegion;
    @Column(nullable=false, length=45)
    private String city;
    @OneToMany(mappedBy="airport")
    private Set<Route> route;
    @OneToMany(mappedBy="airport2")
    private Set<Route> route2;
    @ManyToOne(optional=false)
    @JoinColumn(name="time_zone_id", nullable=false)
    private TimeZone timeZone;

    /** Default constructor. */
    public Airport() {
        super();
    }

    /**
     * Access method for iataId.
     *
     * @return the current value of iataId
     */
    public String getIataId() {
        return iataId;
    }

    /**
     * Setter method for iataId.
     *
     * @param aIataId the new value for iataId
     */
    public void setIataId(String aIataId) {
        iataId = aIataId;
    }

    /**
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for isoRegion.
     *
     * @return the current value of isoRegion
     */
    public String getIsoRegion() {
        return isoRegion;
    }

    /**
     * Setter method for isoRegion.
     *
     * @param aIsoRegion the new value for isoRegion
     */
    public void setIsoRegion(String aIsoRegion) {
        isoRegion = aIsoRegion;
    }

    /**
     * Access method for city.
     *
     * @return the current value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for city.
     *
     * @param aCity the new value for city
     */
    public void setCity(String aCity) {
        city = aCity;
    }

    /**
     * Access method for route.
     *
     * @return the current value of route
     */
    public Set<Route> getRoute() {
        return route;
    }

    /**
     * Setter method for route.
     *
     * @param aRoute the new value for route
     */
    public void setRoute(Set<Route> aRoute) {
        route = aRoute;
    }

    /**
     * Access method for route2.
     *
     * @return the current value of route2
     */
    public Set<Route> getRoute2() {
        return route2;
    }

    /**
     * Setter method for route2.
     *
     * @param aRoute2 the new value for route2
     */
    public void setRoute2(Set<Route> aRoute2) {
        route2 = aRoute2;
    }

    /**
     * Access method for timeZone.
     *
     * @return the current value of timeZone
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Setter method for timeZone.
     *
     * @param aTimeZone the new value for timeZone
     */
    public void setTimeZone(TimeZone aTimeZone) {
        timeZone = aTimeZone;
    }

    /**
     * Compares the key for this instance with another Airport.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Airport and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Airport)) {
            return false;
        }
        Airport that = (Airport) other;
        Object myIataId = this.getIataId();
        Object yourIataId = that.getIataId();
        if (myIataId==null ? yourIataId!=null : !myIataId.equals(yourIataId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Airport.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Airport)) return false;
        return this.equalKeys(other) && ((Airport)other).equalKeys(this);
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
        if (getIataId() == null) {
            i = 0;
        } else {
            i = getIataId().hashCode();
        }
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
        StringBuffer sb = new StringBuffer("[Airport |");
        sb.append(" iataId=").append(getIataId());
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
        ret.put("iataId", getIataId());
        return ret;
    }

}
