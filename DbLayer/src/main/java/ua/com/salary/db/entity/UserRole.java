package ua.com.salary.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Entity
@Table(name = "user_role")
public class UserRole extends ABasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private long id;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole(String role, User user) {
        this.role = role;
        this.user = user;
    }

    public UserRole() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRole userRole = (UserRole) obj;
        return Objects.equal(this.id, userRole.id) && Objects.equal(this.role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.role);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.id).addValue(this.role).toString();
    }

}
