package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class Comment implements Serializable {

    Delivery delivery;
    int rating;
    String content;

    public Comment() {

    }

    public Comment(Delivery d, int rating, String content){
        this.delivery = d;
        this.rating = rating;
        this.content = content;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
