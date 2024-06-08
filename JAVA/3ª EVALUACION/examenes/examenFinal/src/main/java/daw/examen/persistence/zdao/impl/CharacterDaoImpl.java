package daw.examen.persistence.zdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daw.examen.domain.entinty.Character;
import daw.examen.persistence.zdao.CharacterDao;
import daw.examen.persistence.zdao.impl.dbconnection.RawSQL;
import daw.examen.persistence.zdao.mapper.CharacterMapper;

public class CharacterDaoImpl implements CharacterDao {

    @Override
    public Character findById(Integer id) {

        String sql = "SELECT * FROM `characters` WHERE id = ?";
        List<Object> params = List.of(id);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return CharacterMapper.toCharacter(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("Error to find character by id", e);
        }
    }

    @Override
    public List<Character> findAllByGameCode(String id) {

        String sql = "SELECT * FROM `characters` JOIN `games_characters` ON character.id = games_characters.character_id WHERE game_id = ?";
        List<Object> params = List.of(id);

        try {
            return CharacterMapper.toCharacterList(RawSQL.select(sql, params));
        } catch (SQLException e) {
            throw new RuntimeException("Error to find all characters by movie id", e);
        }
    }
}