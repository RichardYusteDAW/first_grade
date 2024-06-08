package daw.examen.persistence.zdao;

import java.util.List;

import daw.examen.domain.entinty.Actor;

public interface ActorDao {

    Actor findById(Integer id);

    Actor findByName(String name);

    List<Actor> findAll();

    List<Actor> findAllByMovieId(Integer id);

}