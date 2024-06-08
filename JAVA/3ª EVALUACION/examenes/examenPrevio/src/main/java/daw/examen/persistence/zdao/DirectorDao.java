package daw.examen.persistence.zdao;

import java.util.List;

import daw.examen.domain.entinty.Director;

public interface DirectorDao {

    Director findById(Integer id);

    Director findByName(String name);

    Director findByMovieId(Integer id);

    List<Director> findAll();
}