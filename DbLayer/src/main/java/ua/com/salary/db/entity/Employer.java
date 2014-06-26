package ua.com.salary.db.entity;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
@Entity
@Table(name = "employer")
@NamedQueries({
        @NamedQuery(name = "getEmployerById", query = "select object(p) from Employer p where p.id = :id"),
        @NamedQuery(name = "getEmployers", query = "select l from Employer l")
})
public class Employer extends ABasicEntity {

    private static final long serialVersionUID = 3601688869238389809L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "empl_id", nullable = false, length = 20)
    private long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id", nullable = false)
    private Department department;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employer employer = (Employer) obj;
        return Objects.equal(this.id, employer.id) && Objects.equal(this.firstName, employer.firstName) &&
                Objects.equal(this.lastName, employer.lastName) && Objects.equal(this.phoneNumber, employer.phoneNumber) &&
                Objects.equal(this.department, employer.department);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.firstName, this.lastName, this.phoneNumber);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", this.id).addValue(this.firstName).addValue(this.lastName).
                addValue(this.phoneNumber).addValue(this.department).toString();
    }

}
