package se.chalmers.cse.wm1819.dit341template.model;

public class Review {
    private int Rating;
    private String ReviewText;
    private String Username;
    private String Movie_id;

    public Review(){
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getReviewText() {
        return ReviewText;
    }

    public void setReviewText(String reviewText) {
        ReviewText = reviewText;
    }

    public String getUser() {
        return Username;
    }

    public void setUser(String user) {
        Username = user;
    }

    public String Movie_id() {
        return Movie_id;
    }

    public void Movie_id(String movie) {
        Movie_id = movie;
    }
}
