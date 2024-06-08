package daw.examen.domain.service.impl;

import java.util.List;

import daw.examen.common.exception.ExamException;
import daw.examen.domain.entinty.Actor;
import daw.examen.domain.service.ActorService;
import daw.examen.persistence.repository.ActorRepository;

public class ActorServiceImpl implements ActorService {

    ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor findById(Integer id) {

        Actor actor = actorRepository.findById(id);
        if (actor == null) {
            throw new ExamException("This actor does not exist");
        }

        return actor;
    }

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findByName(String name) {

        Actor actor = actorRepository.findByName(name);
        if (actor == null) {
            throw new ExamException("This actor does not exist");
        }

        return actor;
    }
}