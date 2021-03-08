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

@Entity(name="booking_payment")
public class BookingPayment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8063640018335277732L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(nullable=false, length=1)
    private boolean refunded;
    @Column(name="stripe_id", nullable=false, length=64)
    private String stripeId;
    @ManyToOne(optional=false)
    @JoinColumn(name="booking_id", nullable=false)
    private Booking booking;

    /** Default constructor. */
    public BookingPayment() {
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
     * Access method for refunded.
     *
     * @return true if and only if refunded is currently true
     */
    public boolean isRefunded() {
        return refunded;
    }

    /**
     * Setter method for refunded.
     *
     * @param aRefunded the new value for refunded
     */
    public void setRefunded(boolean aRefunded) {
        refunded = aRefunded;
    }

    /**
     * Access method for stripeId.
     *
     * @return the current value of stripeId
     */
    public String getStripeId() {
        return stripeId;
    }

    /**
     * Setter method for stripeId.
     *
     * @param aStripeId the new value for stripeId
     */
    public void setStripeId(String aStripeId) {
        stripeId = aStripeId;
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
     * Compares the key for this instance with another BookingPayment.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class BookingPayment and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof BookingPayment)) {
            return false;
        }
        BookingPayment that = (BookingPayment) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another BookingPayment.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BookingPayment)) return false;
        return this.equalKeys(other) && ((BookingPayment)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[BookingPayment |");
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
