package daw.examen.common.IoC;

import daw.examen.domain.service.CharacterService;
import daw.examen.domain.service.impl.CharacterServiceImpl;
import daw.examen.persistence.repository.CharacterRepository;
import daw.examen.persistence.repository.impl.CharacterRepositoryImpl;
import daw.examen.persistence.zdao.CharacterDao;
import daw.examen.persistence.zdao.impl.CharacterDaoImpl;

public class CharacterIoC {

    private static CharacterService characterService;
    private static CharacterRepository characterRepository;
    private static CharacterDao characterDao;

    public static CharacterService getCharacterService() {
        if (characterService == null) {
            characterService = new CharacterServiceImpl(getCharacterRepository());
        }
        return characterService;
    }

    public static CharacterRepository getCharacterRepository() {
        if (characterRepository == null) {
            characterRepository = new CharacterRepositoryImpl(getCharacterDao());
        }
        return characterRepository;
    }

    public static CharacterDao getCharacterDao() {
        if (characterDao == null) {
            characterDao = new CharacterDaoImpl();
        }
        return characterDao;
    }
}