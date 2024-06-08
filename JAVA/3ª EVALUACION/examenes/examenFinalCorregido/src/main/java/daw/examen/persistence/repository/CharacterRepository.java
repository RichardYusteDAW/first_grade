package daw.examen.persistence.repository;

import daw.examen.domain.entinty.Character;

public interface CharacterRepository {
    Character findById(Integer id);
}