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

@Entity(name="ticket")
public class Ticket implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8432258625851404725L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="is_canceled", nullable=false, precision=3)
    private short isCanceled;
    @ManyToOne
    @JoinColumn(name="booking_agent_id")
    private BookingAgent bookingAgent;
    @ManyToOne(optional=false)
    @JoinColumn(name="booking_id", nullable=false)
    private Booking booking;
    @ManyToOne(optional=false)
    @JoinColumn(name="flight_bookings_id", nullable=false)
    private FlightBookings flightBookings;
    @ManyToOne
    @JoinColumn(name="booking_guest_id")
    private BookingGuest bookingGuest;
    @ManyToOne(optional=false)
    @JoinColumn(name="passenger_id", nullable=false)
    private Passenger passenger;
    @ManyToOne(optional=false)
    @JoinColumn(name="booking_payment_id", nullable=false)
    private BookingPayment bookingPayment;
    @ManyToOne
    @JoinColumn(name="booking_user_id")
    private BookingUser bookingUser;

    /** Default constructor. */
    public Ticket() {
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
     * Access method for isCanceled.
     *
     * @return the current value of isCanceled
     */
    public short getIsCanceled() {
        return isCanceled;
    }

    /**
     * Setter method for isCanceled.
     *
     * @param aIsCanceled the new value for isCanceled
     */
    public void setIsCanceled(short aIsCanceled) {
        isCanceled = aIsCanceled;
    }

    /**
     * Access method for bookingAgent.
     *
     * @return the current value of bookingAgent
     */
    public BookingAgent getBookingAgent() {
        return bookingAgent;
    }

    /**
     * Setter method for bookingAgent.
     *
     * @param aBookingAgent the new value for bookingAgent
     */
    public void setBookingAgent(BookingAgent aBookingAgent) {
        bookingAgent = aBookingAgent;
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
     * Access method for flightBookings.
     *
     * @return the current value of flightBookings
     */
    public FlightBookings getFlightBookings() {
        return flightBookings;
    }

    /**
     * Setter method for flightBookings.
     *
     * @param aFlightBookings the new value for flightBookings
     */
    public void setFlightBookings(FlightBookings aFlightBookings) {
        flightBookings = aFlightBookings;
    }

    /**
     * Access method for bookingGuest.
     *
     * @return the current value of bookingGuest
     */
    public BookingGuest getBookingGuest() {
        return bookingGuest;
    }

    /**
     * Setter method for bookingGuest.
     *
     * @param aBookingGuest the new value for bookingGuest
     */
    public void setBookingGuest(BookingGuest aBookingGuest) {
        bookingGuest = aBookingGuest;
    }

    /**
     * Access method for passenger.
     *
     * @return the current value of passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Setter method for passenger.
     *
     * @param aPassenger the new value for passenger
     */
    public void setPassenger(Passenger aPassenger) {
        passenger = aPassenger;
    }

    /**
     * Access method for bookingPayment.
     *
     * @return the current value of bookingPayment
     */
    public BookingPayment getBookingPayment() {
        return bookingPayment;
    }

    /**
     * Setter method for bookingPayment.
     *
     * @param aBookingPayment the new value for bookingPayment
     */
    public void setBookingPayment(BookingPayment aBookingPayment) {
        bookingPayment = aBookingPayment;
    }

    /**
     * Access method for bookingUser.
     *
     * @return the current value of bookingUser
     */
    public BookingUser getBookingUser() {
        return bookingUser;
    }

    /**
     * Setter method for bookingUser.
     *
     * @param aBookingUser the new value for bookingUser
     */
    public void setBookingUser(BookingUser aBookingUser) {
        bookingUser = aBookingUser;
    }

    /**
     * Compares the key for this instance with another Ticket.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Ticket and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Ticket)) {
            return false;
        }
        Ticket that = (Ticket) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Ticket.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Ticket)) return false;
        return this.equalKeys(other) && ((Ticket)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Ticket |");
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
