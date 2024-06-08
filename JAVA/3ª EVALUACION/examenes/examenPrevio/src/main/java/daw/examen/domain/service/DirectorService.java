package daw.examen.domain.service;

import java.util.List;

import daw.examen.domain.entinty.Director;

public interface DirectorService {

    Director findById(Integer id);

    List<Director> findAll();

    Director findByName(String name);
}
