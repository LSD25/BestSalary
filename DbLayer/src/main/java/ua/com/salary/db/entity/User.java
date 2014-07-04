package ua.com.salary.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "findUserById", query = "select object(u) from User u where u.id = :id"),
        @NamedQuery(name = "findUserByName", query = "select object(u) from User u where u.username = :username")
})
public class User extends ABasicEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<UserRole> userRole;

    @Column(name = "is_account_expired", nullable = false)
    private boolean isAccountExpired;

    @Column(name = "is_account_non_blocked", nullable = false)
    private boolean isAccountNonBlocked;

    @Column(name = "is_credential_not_expired", nullable = false)
    private boolean isCredentialNotExpired;

    @Transient
    private List<GrantedAuthority> authorities;

    public User(String username, String password, boolean isEnabled, boolean isAccountExpired,
                boolean isAccountNonBlocked, boolean isCredentialNotExpired) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountExpired = isAccountExpired;
        this.isAccountNonBlocked = isAccountNonBlocked;
        this.isCredentialNotExpired = isCredentialNotExpired;
    }

    public User(String userName, String password, boolean isEnabled, boolean isAccountExpired,
                boolean isAccountNonBlocked, boolean isCredentialNotExpired, List<GrantedAuthority> authorities) {
        this(userName, password, isEnabled, isAccountExpired, isAccountNonBlocked, isCredentialNotExpired);
        this.authorities = authorities;
    }

    public User(String userName, String password, boolean isEnabled, boolean isAccountExpired,
                boolean isAccountNonBlocked, boolean isCredentialNotExpired, Set<UserRole> userRole) {
        this(userName, password, isEnabled, isAccountExpired, isAccountNonBlocked, isCredentialNotExpired);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialNotExpired;
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
                Objects.equal(this.password, user.password) && Objects.equal(this.isEnabled, user.isEnabled);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.username, this.password, this.isEnabled);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.id).addValue(this.username).addValue(this.password).addValue(this.isEnabled).toString();
    }

}
