package daw.examen.domain.entinty;

import java.util.List;

public class Movie {

    private Integer id;
    private String title;
    private Director director;
    private List<Actor> actorList;
    private Integer year;

    // Constructor without director and actorList (for MovieMapper)
    public Movie(Integer id, String title, Integer year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    // Constructor without id (for MovieServiceImpl)
    public Movie(String title, Director director, List<Actor> actorList, Integer year) {
        this.title = title;
        this.director = director;
        this.actorList = actorList;
        this.year = year;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return this.director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActorList() {
        return this.actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
