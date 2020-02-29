package fr.unice.polytech.isa.dd.teamH.entities;

public class Comment {

    int rating;
    String content;

    public Comment(){

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
}
