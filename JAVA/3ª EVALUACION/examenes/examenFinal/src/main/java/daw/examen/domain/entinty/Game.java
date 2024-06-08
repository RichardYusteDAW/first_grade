package daw.examen.domain.entinty;

import java.util.List;

public class Game {

    private String code;
    private String title;
    private Integer year;
    private String description;
    private Director director;
    private List<Character> characterList;

    // Constructor
    public Game(String code, String title, Integer year, String description, Director director,
            List<Character> characterList) {
        this.code = code;
        this.title = title;
        this.year = year;
        this.description = description;
        this.director = director;
        this.characterList = characterList;
    }

    // Constructor without CharacterList
    public Game(String code, String title, Integer year, String description, Director director) {
        this.code = code;
        this.title = title;
        this.year = year;
        this.description = description;
        this.director = director;
    }

    // Constructor without Director and CharacterList
    public Game(String code, String title, Integer year, String description) {
        this.code = code;
        this.title = title;
        this.year = year;
        this.description = description;
    }

    // Getters and Setters
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Director getDirector() {
        return this.director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Character> getCharacterList() {
        return this.characterList;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

}