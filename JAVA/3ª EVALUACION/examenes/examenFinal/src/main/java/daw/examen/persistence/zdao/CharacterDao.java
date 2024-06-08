package daw.examen.persistence.zdao;

import java.util.List;

import daw.examen.domain.entinty.Character;

public interface CharacterDao {

    Character findById(Integer id);

    List<Character> findAllByGameCode(String code);
}