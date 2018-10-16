package se.chalmers.cse.wm1819.dit341template.model;

public class Review {
    public int Rating;
    public String ReviewText;
    public String User;
    public Movie Movie;

     Review(){
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
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public se.chalmers.cse.wm1819.dit341template.model.Movie getMovie() {
        return Movie;
    }

    public void setMovie(se.chalmers.cse.wm1819.dit341template.model.Movie movie) {
        Movie = movie;
    }
}
