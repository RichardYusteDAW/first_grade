package daw.examen.domain.service.impl;

import daw.examen.common.exception.ExamException;
import daw.examen.domain.entinty.Character;
import daw.examen.domain.service.CharacterService;
import daw.examen.persistence.repository.CharacterRepository;

public class CharacterServiceImpl implements CharacterService {

    CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public Character findById(Integer id) {
        Character character = characterRepository.findById(id);

        if (character == null) {
            throw new ExamException("This character doesn´t exist");
        }

        return character;
    }
}