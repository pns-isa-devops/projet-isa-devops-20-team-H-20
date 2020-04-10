package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="comments")
public class Comment implements Serializable {
    private Delivery delivery;
    private int rating;
    private String content;
    private int id;

    public Comment() {

    }

    public Comment(Delivery d, int rating, String content){
        this.delivery = d;
        this.rating = rating;
        this.content = content;
    }

    //for OneToTone set ALL
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    public Delivery getDelivery() {
        return delivery;
    }

    //note sur 10
    @NotNull
    public int getRating() {
        return rating;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getRating() == comment.getRating() &&
                getDelivery().equals(comment.getDelivery()) &&
                Objects.equals(getContent(), comment.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDelivery(), getRating(), getContent());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "delivery=" + delivery +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                '}';
    }
}
