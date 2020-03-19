package fr.unice.polytech.isa.dd.teamH.entities;

import java.io.Serializable;
import java.util.*;

public class Supplier implements Serializable {

    private String name;

    private Set<String> contacts;

    public Supplier() {
        contacts = new HashSet<>();
    }

    public Supplier(String name){
        this.name = name;
        contacts = new HashSet<>();
    }

    public Supplier(String name, String contact) {
        this(name);
        contacts.add(contact);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<String> getContacts() {
        return new HashSet<>(contacts);
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
