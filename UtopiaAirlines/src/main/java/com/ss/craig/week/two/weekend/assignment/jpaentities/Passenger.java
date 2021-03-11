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

@Entity(name="passenger")
public class Passenger implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 986153367890453582L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="seat_number", nullable=false, precision=10)
    private int seatNumber;
    @Column(nullable=false, length=128)
    private String address;
    @Column(nullable=false, length=11)
    private String dob;
    @Column(name="given_name", nullable=false, length=32)
    private String givenName;
    @Column(name="family_name", nullable=false, length=32)
    private String familyName;
    @Column(length=32)
    private String gender;
    @ManyToOne(optional=false)
    @JoinColumn(name="booking_id", nullable=false)
    private Booking booking;
    @ManyToOne(optional=false)
    @JoinColumn(name="airplane_id", nullable=false)
    private Airplane airplane;
    @OneToMany(mappedBy="passenger")
    private Set<Ticket> ticket;

    /** Default constructor. */
    public Passenger() {
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
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
        address = aAddress;
    }

    /**
     * Access method for dob.
     *
     * @return the current value of dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * Setter method for dob.
     *
     * @param aDob the new value for dob
     */
    public void setDob(String aDob) {
        dob = aDob;
    }

    /**
     * Access method for givenName.
     *
     * @return the current value of givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Setter method for givenName.
     *
     * @param aGivenName the new value for givenName
     */
    public void setGivenName(String aGivenName) {
        givenName = aGivenName;
    }

    /**
     * Access method for familyName.
     *
     * @return the current value of familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Setter method for familyName.
     *
     * @param aFamilyName the new value for familyName
     */
    public void setFamilyName(String aFamilyName) {
        familyName = aFamilyName;
    }

    /**
     * Access method for gender.
     *
     * @return the current value of gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter method for gender.
     *
     * @param aGender the new value for gender
     */
    public void setGender(String aGender) {
        gender = aGender;
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
     * Access method for ticket.
     *
     * @return the current value of ticket
     */
    public Set<Ticket> getTicket() {
        return ticket;
    }

    /**
     * Setter method for ticket.
     *
     * @param aTicket the new value for ticket
     */
    public void setTicket(Set<Ticket> aTicket) {
        ticket = aTicket;
    }

    /**
     * Compares the key for this instance with another Passenger.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Passenger and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Passenger)) {
            return false;
        }
        Passenger that = (Passenger) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Passenger.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Passenger)) return false;
        return this.equalKeys(other) && ((Passenger)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Passenger |");
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
