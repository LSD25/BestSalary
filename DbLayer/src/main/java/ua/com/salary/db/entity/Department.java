package ua.com.salary.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Victor Zagnitko 25.06.2014.
 */
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "getDepartmentById", query = "select object (p) from Department p where p.id = :id"),
        @NamedQuery(name = "getDepartments", query = "select l from Department l")
})
public class Department extends ABasicEntity {

    private static final long serialVersionUID = 186272967207588539L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dep_id", nullable = false)
    private long id;

    @Column(name = "dep_name", nullable = false, length = 100)
    private String departmentName;

    @JsonManagedReference
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Employer> employers;

    public Department() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(Set<Employer> employers) {
        this.employers = employers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department dep = (Department) obj;
        return Objects.equal(this.id, dep.id) && Objects.equal(this.departmentName, dep.departmentName) &&
                Objects.equal(this.employers, dep.employers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.departmentName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.id).addValue(this.departmentName).addValue(this.employers).toString();
    }

}
