package ua.com.salary.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Entity
@Table
@Audited
@NamedQueries({
        @NamedQuery(name = "findUserById", query = "select object(u) from User u where u.id = :id"),
        @NamedQuery(name = "findUserByName", query = "select object(u) from User u where u.username = :username")
})
@Indexed
public class User extends ABasicEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_name", unique = true)
    @Field
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email", unique = true, nullable = false)
    @Field
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "is_ebay")
    private boolean isEbay;

    @Column(name = "is_trial")
    private boolean isTrial;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<UserRole> userRole;

    @Column(name = "is_account_expired")
    private boolean isAccountExpired;

    @Column(name = "is_account_non_blocked")
    private boolean isAccountNonBlocked = true;

    @Column(name = "is_credential_not_expired")
    private boolean isCredentialNotExpired = true;

    @Transient
    private List<GrantedAuthority> authorities;

    public User(String username, String password, boolean isEbay, boolean isTrial, String firstName, String lastName,
                String companyName, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isEbay = isEbay;
        this.isTrial = isTrial;
    }

    public User(String userName, String password, boolean isEbay, boolean isTrial, List<GrantedAuthority> authorities,
                String firstName, String lastName, String companyName, String email, String phoneNumber) {
        this(userName, password, isEbay, isTrial, firstName, lastName, companyName, email, phoneNumber);
        this.authorities = authorities;
    }

    public User(String userName, String password, boolean isEbay, boolean isTrial, Set<UserRole> userRole, String firstName,
                String lastName, String companyName, String email, String phoneNumber) {
        this(userName, password, isEbay, isTrial, firstName, lastName, companyName, email, phoneNumber);
        this.userRole = userRole;
    }

    public User() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRoleSet(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEbay() {
        return isEbay;
    }

    public void setEbay(boolean isEbay) {
        this.isEbay = isEbay;
    }

    public boolean isTrial() {
        return isTrial;
    }

    public void setTrial(boolean isTrial) {
        this.isTrial = isTrial;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountExpired(boolean isAccountExpired) {
        this.isAccountExpired = isAccountExpired;
    }

    public boolean isAccountNonExpired() {
        return this.isAccountExpired;
    }

    public boolean isAccountNonLocked() {
        return this.isAccountNonBlocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.isCredentialNotExpired;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        return Objects.equal(this.id, user.id) && Objects.equal(this.username, user.username) &&
                Objects.equal(this.password, user.password) && Objects.equal(this.isEnabled, user.isEnabled) &&
                Objects.equal(this.firstName, user.firstName) && Objects.equal(this.lastName, user.lastName) &&
                Objects.equal(this.companyName, user.companyName) && Objects.equal(this.phoneNumber, user.phoneNumber) &&
                Objects.equal(this.isAccountExpired, user.isAccountExpired) &&
                Objects.equal(this.isAccountNonBlocked, user.isAccountNonBlocked) &&
                Objects.equal(this.isCredentialNotExpired, user.isCredentialNotExpired) &&
                Objects.equal(this.isEbay, user.isEbay) && Objects.equal(this.isTrial, user.isTrial) &&
                Objects.equal(this.registrationDate, user.registrationDate) && Objects.equal(this.email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.username, this.password, this.isEnabled, this.firstName, this.lastName,
                this.companyName, this.phoneNumber, this.isAccountExpired, this.isAccountNonBlocked,
                this.isCredentialNotExpired, this.isEbay, this.isTrial, this.registrationDate, this.email);
    }

//    @Override
//    public String toString() {
//        return Objects.toStringHelper(this).addValue(this.id).addValue(this.username).addValue(this.password).
//                addValue(this.isEnabled).addValue(this.firstName).addValue(this.lastName).addValue(this.companyName).
//                addValue(this.phoneNumber).addValue(this.isAccountExpired).addValue(this.isAccountNonBlocked).
//                addValue(this.isCredentialNotExpired).addValue(this.isEbay).addValue(this.isTrial).
//                addValue(this.registrationDate).addValue(this.email).toString();
//    }

}
