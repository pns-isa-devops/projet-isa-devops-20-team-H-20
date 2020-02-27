package fr.polytech.unice.isa.dd.teamH.entities;

public class Supplier {
    private String name;
    private String email;

    public Supplier(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
