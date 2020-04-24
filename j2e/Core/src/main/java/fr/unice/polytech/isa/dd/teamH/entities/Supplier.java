package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Supplier implements Serializable {

    private String name;
    private Set<String> contacts = new HashSet<>();

    public Supplier() {
    }

    public Supplier(String name){
        this.name = name;
    }

    public Supplier(String name, String contact) {
        this(name);
        contacts.add(contact);
    }

    @Id
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    //@OneToMany(cascade = CascadeType.PERSIST)
    @ElementCollection(targetClass=String.class, fetch=FetchType.EAGER)
    @Column(name="contacts")
    @CollectionTable(name="contacts_table", joinColumns=@JoinColumn(name="contacts_id"))
    public Set<String> getContacts() {
        return contacts;
    }
    public void setContacts(Set<String> contacts){
        this.contacts = contacts;
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
        return "Supplier{" +
                "name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
