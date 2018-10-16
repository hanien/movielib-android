package se.chalmers.cse.wm1819.dit341template.model;

public class Movie {
    private int ReleaseYear;
    private  String MovieTitle;
    private  String PlotDescription;
    private String Director;
    private String Trailer;
    private String MainPoster;
    private String[] MainActors;
    private String _id;

    public Movie(){
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String[] getMainActors() {
        return MainActors;
    }

    public void setMainActors(String[] mainActors) {
        MainActors = mainActors;
    }

    public String get_id() {
        return _id;
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
