package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
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
    public int hashCode() {
        int result = getContent().hashCode();
        result = 31*result+getRating();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Comment))
            return false;
        Comment item = (Comment) o;
        if (getRating() != item.getRating())
            return false;
        return getContent().equals(item.getContent());
    }

    @Override
    public String toString() { return rating + " stars - " + content; }
}
