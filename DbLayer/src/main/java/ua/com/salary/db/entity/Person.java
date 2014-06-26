package ua.com.salary.db.entity;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * @author Victor Zagnitko on 18.06.2014.
 */
@Entity
@Table(name = "person")
@NamedQueries({
        @NamedQuery(name = "getPersonById", query = "select object(p) from Person p where p.id = :id"),
        @NamedQuery(name = "getPersons", query = "select l from Person l")
})
public class Person extends ABasicEntity {

    private static final long serialVersionUID = -712632639504749203L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 10, nullable = false)
    private long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "age", length = 6, nullable = false)
    private int age;

    public Person() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.name, this.age);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person person = (Person) obj;
        return Objects.equal(this.id, person.id) && Objects.equal(this.name, person.name) && Objects.equal(this.age, person.age);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.id).addValue(this.name).addValue(this.age).toString();
    }

}
