package daw.examen.persistence.zdao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daw.examen.domain.entinty.Movie;

public class MovieMapper {

    public static Movie toMovie(ResultSet resultSet) throws SQLException {

        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        Integer year = resultSet.getInt("year");

        return new Movie(id, title, year);

    }

    public static List<Movie> toMovieList(ResultSet resultSet) throws SQLException {

        List<Movie> movieList = new ArrayList<>();

        while (resultSet.next()) {
            movieList.add(toMovie(resultSet));
        }

        return movieList;
    }
}