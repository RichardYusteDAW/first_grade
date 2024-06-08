package daw.examen.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import daw.examen.common.IoC.ActorIoC;
import daw.examen.common.IoC.DirectorIoC;
import daw.examen.common.IoC.MovieIoC;
import daw.examen.domain.service.ActorService;
import daw.examen.domain.service.DirectorService;
import daw.examen.domain.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService = MovieIoC.getMovieService();
    private final DirectorService directorService = DirectorIoC.getDirectorService();
    private final ActorService actorService = ActorIoC.getActorService();

    @GetMapping()
    public String findAll(Model model) {

        // To see the list of movies
        model.addAttribute("movieList", movieService.findAll());

        return "movies.html";
    }

    @PostMapping()
    public String createMovie(@RequestParam String title, @RequestParam String directorName,
            @RequestParam List<String> actorNameList, @RequestParam Integer year) {

        movieService.create(title, directorName, actorNameList, year);

        return "redirect:/movies";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Integer id) {

        // To see the movie details
        model.addAttribute("movie", movieService.findById(id));

        // To edit the movie
        model.addAttribute("directorList", directorService.findAll());
        model.addAttribute("actorList", actorService.findAll());

        return "movieDetail.html";
    }

    @PutMapping("/{id}")
    public String putMethodName(@PathVariable Integer id, @RequestParam String title, @RequestParam String directorName,
            @RequestParam List<String> actorNameList, @RequestParam Integer year) {

        movieService.update(id, title, directorName, actorNameList, year);

        return "redirect:/movies";
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Integer id) {

        movieService.delete(id);

        return "redirect:/movies";
    }

    @GetMapping("/new")
    public String form(Model model) {

        // To create a new movie
        model.addAttribute("directorList", directorService.findAll());
        model.addAttribute("actorList", actorService.findAll());

        return "form.html";
    }

    @GetMapping("/search")
    public String search(Model model) {

        // To search for a movie
        model.addAttribute("directorList", directorService.findAll());
        model.addAttribute("actorList", actorService.findAll());

        return "search.html";
    }

    @PostMapping("/search/title")
    public String searchByTitle(Model model, @RequestParam String title) {

        // To see the list of movies
        model.addAttribute("movieList", movieService.findAllByTitle(title));

        return "movies.html";
    }

    @PostMapping("/search/director")
    public String searchByDirector(Model model, @RequestParam String directorName) {

        // To see the list of movies
        model.addAttribute("movieList", movieService.findAllByDirectorName(directorName));

        return "movies.html";
    }

    @PostMapping("/search/actor")
    public String searchByActor(Model model, @RequestParam String actorName) {

        // To see the list of movies
        model.addAttribute("movieList", movieService.findAllByActorName(actorName));

        return "movies.html";
    }

    @PostMapping("/search/year")
    public String searchByYear(Model model, @RequestParam Integer year) {

        // To see the list of movies
        model.addAttribute("movieList", movieService.findAllByYear(year));

        return "movies.html";
    }

}