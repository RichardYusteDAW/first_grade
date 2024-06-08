package daw.examen.persistence.repository.impl;

import daw.examen.domain.entinty.Character;
import daw.examen.persistence.repository.CharacterRepository;
import daw.examen.persistence.zdao.CharacterDao;

public class CharacterRepositoryImpl implements CharacterRepository {

    CharacterDao characterDao;

    public CharacterRepositoryImpl(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    @Override
    public Character findById(Integer id) {
        return characterDao.findById(id);
    }
}