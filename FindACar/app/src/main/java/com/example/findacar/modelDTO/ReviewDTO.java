package com.example.findacar.modelDTO;

public class ReviewDTO {

    private long resId;
    private String comment;
    private String rating;
    private String email;

    public ReviewDTO(long resId, String comment, String rating, String email) {
        this.resId = resId;
        this.comment = comment;
        this.rating = rating;
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
