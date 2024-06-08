package daw.examen.persistence.repository;

import java.util.List;

import daw.examen.domain.entinty.Actor;

public interface ActorRepository {

    Actor findById(Integer id);

    List<Actor> findAll();

    Actor findByName(String name);
}
