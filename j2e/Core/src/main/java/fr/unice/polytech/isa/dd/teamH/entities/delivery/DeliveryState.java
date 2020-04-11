package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.Objects;

@XmlSeeAlso({CompletedDeliveryState.class,
            FailedDeliveryState.class,
            InFlightDeliveryState.class,
            NotSentDeliveryState.class})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="delivery_state_type")
public abstract class DeliveryState implements Serializable {
    protected String name;

    public abstract boolean isCompleted();

    public boolean is(String name) {
        return name.equals(this.name);
    }

    public abstract DeliveryState clone();

    @Id
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryState that = (DeliveryState) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
