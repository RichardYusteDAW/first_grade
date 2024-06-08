package daw.examen.domain.entinty;

public class Character {

    private Integer id;
    private String name;
    private String rol;
    private String description;

    // Constructor
    public Character(Integer id, String name, String rol, String description) {
        this.id = id;
        this.name = name;
        this.rol = rol;
        this.description = description;
    }

    // Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}