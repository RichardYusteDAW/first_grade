package daw.examen.persistence.zdao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daw.examen.domain.entinty.Character;

public class CharacterMapper {

    public static Character toCharacter(ResultSet resultSet) throws SQLException {

        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String rol = resultSet.getString("role");
        String description = resultSet.getString("description");

        return new Character(id, name, rol, description);
    }

    public static List<Character> toCharacterList(ResultSet resultSet) throws SQLException {

        List<Character> characterList = new ArrayList<>();

        while (resultSet.next()) {
            characterList.add(toCharacter(resultSet));
        }

        return characterList;
    }
}