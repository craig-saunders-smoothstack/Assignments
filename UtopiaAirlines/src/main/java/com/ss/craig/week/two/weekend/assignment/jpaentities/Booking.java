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

@Entity(name="booking")
public class Booking implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5070581340833507795L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="is_active", nullable=false, precision=3)
    private short isActive;
    @Column(name="confirmation_code", nullable=false, length=64)
    private String confirmationCode;
    @OneToMany(mappedBy="booking")
    private Set<FlightBookings> flightBookings;
    @OneToMany(mappedBy="booking")
    private Set<BookingGuest> bookingGuest;
    @OneToMany(mappedBy="booking")
    private Set<BookingAgent> bookingAgent;
    @OneToMany(mappedBy="booking")
    private Set<Passenger> passenger;
    @OneToMany(mappedBy="booking")
    private Set<BookingPayment> bookingPayment;
    @OneToMany(mappedBy="booking")
    private Set<Ticket> ticket;
    @OneToMany(mappedBy="booking")
    private Set<BookingUser> bookingUser;

    /** Default constructor. */
    public Booking() {
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
     * Access method for isActive.
     *
     * @return the current value of isActive
     */
    public short getIsActive() {
        return isActive;
    }

    /**
     * Setter method for isActive.
     *
     * @param aIsActive the new value for isActive
     */
    public void setIsActive(short aIsActive) {
        isActive = aIsActive;
    }

    /**
     * Access method for confirmationCode.
     *
     * @return the current value of confirmationCode
     */
    public String getConfirmationCode() {
        return confirmationCode;
    }

    /**
     * Setter method for confirmationCode.
     *
     * @param aConfirmationCode the new value for confirmationCode
     */
    public void setConfirmationCode(String aConfirmationCode) {
        confirmationCode = aConfirmationCode;
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
     * Access method for bookingGuest.
     *
     * @return the current value of bookingGuest
     */
    public Set<BookingGuest> getBookingGuest() {
        return bookingGuest;
    }

    /**
     * Setter method for bookingGuest.
     *
     * @param aBookingGuest the new value for bookingGuest
     */
    public void setBookingGuest(Set<BookingGuest> aBookingGuest) {
        bookingGuest = aBookingGuest;
    }

    /**
     * Access method for bookingAgent.
     *
     * @return the current value of bookingAgent
     */
    public Set<BookingAgent> getBookingAgent() {
        return bookingAgent;
    }

    /**
     * Setter method for bookingAgent.
     *
     * @param aBookingAgent the new value for bookingAgent
     */
    public void setBookingAgent(Set<BookingAgent> aBookingAgent) {
        bookingAgent = aBookingAgent;
    }

    /**
     * Access method for passenger.
     *
     * @return the current value of passenger
     */
    public Set<Passenger> getPassenger() {
        return passenger;
    }

    /**
     * Setter method for passenger.
     *
     * @param aPassenger the new value for passenger
     */
    public void setPassenger(Set<Passenger> aPassenger) {
        passenger = aPassenger;
    }

    /**
     * Access method for bookingPayment.
     *
     * @return the current value of bookingPayment
     */
    public Set<BookingPayment> getBookingPayment() {
        return bookingPayment;
    }

    /**
     * Setter method for bookingPayment.
     *
     * @param aBookingPayment the new value for bookingPayment
     */
    public void setBookingPayment(Set<BookingPayment> aBookingPayment) {
        bookingPayment = aBookingPayment;
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
     * Access method for bookingUser.
     *
     * @return the current value of bookingUser
     */
    public Set<BookingUser> getBookingUser() {
        return bookingUser;
    }

    /**
     * Setter method for bookingUser.
     *
     * @param aBookingUser the new value for bookingUser
     */
    public void setBookingUser(Set<BookingUser> aBookingUser) {
        bookingUser = aBookingUser;
    }

    /**
     * Compares the key for this instance with another Booking.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Booking and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Booking)) {
            return false;
        }
        Booking that = (Booking) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Booking.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Booking)) return false;
        return this.equalKeys(other) && ((Booking)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Booking |");
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
