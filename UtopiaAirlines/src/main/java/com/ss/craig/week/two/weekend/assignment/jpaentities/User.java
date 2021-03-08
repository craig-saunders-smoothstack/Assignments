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
@Table(name="user", indexes={@Index(name="user_username_IX", columnList="username", unique=true)})
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8478658090013687753L;

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(nullable=false, length=64)
    private String email;
    @Column(name="family_name", nullable=false, length=32)
    private String familyName;
    @Column(name="given_name", nullable=false, length=32)
    private String givenName;
    @Column(nullable=false, length=64)
    private String password;
    @Column(nullable=false, length=16)
    private String phone;
    @Column(unique=true, nullable=false, length=64)
    private String username;
    @OneToMany(mappedBy="user")
    private Set<BookingAgent> bookingAgent;
    @ManyToOne(optional=false)
    @JoinColumn(name="role_id", nullable=false)
    private UserRole userRole;

    /** Default constructor. */
    public User() {
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
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
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
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * Access method for phone.
     *
     * @return the current value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for phone.
     *
     * @param aPhone the new value for phone
     */
    public void setPhone(String aPhone) {
        phone = aPhone;
    }

    /**
     * Access method for username.
     *
     * @return the current value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for username.
     *
     * @param aUsername the new value for username
     */
    public void setUsername(String aUsername) {
        username = aUsername;
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
     * Access method for userRole.
     *
     * @return the current value of userRole
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Setter method for userRole.
     *
     * @param aUserRole the new value for userRole
     */
    public void setUserRole(UserRole aUserRole) {
        userRole = aUserRole;
    }

    /**
     * Compares the key for this instance with another User.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class User and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another User.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) return false;
        return this.equalKeys(other) && ((User)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[User |");
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
