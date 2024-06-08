package daw.examen.persistence.zdao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daw.examen.domain.entinty.Director;

public class DirectorMapper {

    public static Director toDirector(ResultSet resultSet) throws SQLException {

        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Director(id, name);
    }

    public static List<Director> toDirectorList(ResultSet resultSet) throws SQLException {

        List<Director> directorList = new ArrayList<>();

        while (resultSet.next()) {
            directorList.add(toDirector(resultSet));
        }

        return directorList;
    }
}