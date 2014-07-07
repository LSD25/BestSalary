package ua.com.salary.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.salary.common.validate.ValidatePattern;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class User extends ABasicEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @NotBlank
    @Size(min = 5)
    @Column(name = "user_name", unique = true)
    private String username;

    @NotBlank
    @Size(min = 2)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Size(min = 3)
    @Column(name = "company_name")
    private String companyName;

    @NotBlank
    @Pattern(regexp = ValidatePattern.PHONE_NUMBER_VALIDATE_REG_EXP)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Size(min = 6)
    @Pattern(regexp = ValidatePattern.PASSWORD_VALIDATE_REG_EXP)
    @Column(name = "password")
    private String password;

    @Transient
    @NotBlank
    @Size(min = 6)
    @Pattern(regexp = ValidatePattern.PASSWORD_VALIDATE_REG_EXP)
    private String confirmPassword;

    @NotBlank
    @Past
    @DateTimeFormat(pattern = ValidatePattern.DATE_FORMAT_PATTERN)
    @Column(name = "registration_date")
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
                String companyName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.isEbay = isEbay;
        this.isTrial = isTrial;
    }

    public User(String userName, String password, boolean isEbay, boolean isTrial, List<GrantedAuthority> authorities,
                String firstName, String lastName, String companyName, String phoneNumber) {
        this(userName, password, isEbay, isTrial, firstName, lastName, companyName, phoneNumber);
        this.authorities = authorities;
    }

    public User(String userName, String password, boolean isEbay, boolean isTrial, Set<UserRole> userRole, String firstName,
                String lastName, String companyName, String phoneNumber) {
        this(userName, password, isEbay, isTrial, firstName, lastName, companyName, phoneNumber);
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
                Objects.equal(this.registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.username, this.password, this.isEnabled, this.firstName, this.lastName,
                this.companyName, this.phoneNumber, this.isAccountExpired, this.isAccountNonBlocked,
                this.isCredentialNotExpired, this.isEbay, this.isTrial, this.registrationDate);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.id).addValue(this.username).addValue(this.password).
                addValue(this.isEnabled).addValue(this.firstName).addValue(this.lastName).addValue(this.companyName).
                addValue(this.phoneNumber).addValue(this.isAccountExpired).addValue(this.isAccountNonBlocked).
                addValue(this.isCredentialNotExpired).addValue(this.isEbay).addValue(this.isTrial).
                addValue(this.registrationDate).toString();
    }

}
