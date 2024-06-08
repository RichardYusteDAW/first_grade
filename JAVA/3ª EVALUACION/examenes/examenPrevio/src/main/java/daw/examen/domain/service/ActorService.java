package daw.examen.domain.service;

import java.util.List;

import daw.examen.domain.entinty.Actor;

public interface ActorService {

    Actor findById(Integer id);

    List<Actor> findAll();

    Actor findByName(String name);
}
