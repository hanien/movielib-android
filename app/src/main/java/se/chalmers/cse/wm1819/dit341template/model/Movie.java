package se.chalmers.cse.wm1819.dit341template.model;

public class Movie {
    public int ReleaseYear;
    public  String MovieTitle;
    public  String PlotDescription;
    public String Director;
    public String Trailer;
    public String MainPoster;
    public String[] MainActors;
    public String _id;

    Movie(){
    }

    public int getReleaseYear() {
        return ReleaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        ReleaseYear = releaseYear;
    }

    public String getMovieTitle() {
        return MovieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        MovieTitle = movieTitle;
    }

    public String getPlotDescription() {
        return PlotDescription;
    }

    public void setPlotDescription(String plotDescription) {
        PlotDescription = plotDescription;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getTrailer() {
        return Trailer;
    }

    public void setTrailer(String trailer) {
        Trailer = trailer;
    }

    public String getMainPoster() {
        return MainPoster;
    }

    public void setMainPoster(String mainPoster) {
        MainPoster = mainPoster;
    }

}
