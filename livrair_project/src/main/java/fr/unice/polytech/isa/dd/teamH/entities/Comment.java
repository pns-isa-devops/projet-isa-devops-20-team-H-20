package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue
    int id;

    @NotNull
    int rating;

    String content;

    public Comment() {

    }

    public Comment(int rating, String content){
        this.rating = rating;
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getRating() == comment.getRating() &&
                Objects.equals(getContent(), comment.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRating(), getContent());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "rating=" + rating +
                ", content='" + content + '\'' +
                '}';
    }
}
