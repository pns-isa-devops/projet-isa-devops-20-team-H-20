package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

    @Id
    @NotNull
    private String name;

    @ElementCollection
    private List<String> contacts;

    public Supplier() {
    }

    public Supplier(String name){
        this.name = name;
        contacts = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getContacts() {
        return new ArrayList<>(contacts);
    }

    public void addContact(String contact) {
        contacts.add(contact);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return getName().equals(supplier.getName()) &&
                Objects.equals(getContacts(), supplier.getContacts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getContacts());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Supplier: ").append(getName())
                .append("\nList of contacts: \n");
        if(contacts != null){
            for(String s : contacts){
                result.append(s).append("\n");
            }
        }
        return result.toString();
    }
}
