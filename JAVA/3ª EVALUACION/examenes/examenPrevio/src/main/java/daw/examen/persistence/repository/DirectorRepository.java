package daw.examen.persistence.repository;

import java.util.List;

import daw.examen.domain.entinty.Director;

public interface DirectorRepository {

    Director findById(Integer id);

    List<Director> findAll();

    Director findByName(String name);

}
