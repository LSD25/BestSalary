package ua.com.salary.db.entity;

import java.io.Serializable;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
public abstract class ABasicEntity implements Serializable {

    public ABasicEntity() {
        super();
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

}
