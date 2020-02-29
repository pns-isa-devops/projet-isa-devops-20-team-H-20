package fr.unice.polytech.isa.dd.teamH.entities;

public class Supplier {
    private String name;
    private String contact;

    public Supplier() {
    }

    public Supplier(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
