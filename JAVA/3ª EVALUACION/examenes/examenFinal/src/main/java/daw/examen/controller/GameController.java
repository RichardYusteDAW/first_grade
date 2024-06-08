package daw.examen.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import daw.examen.common.IoC.GameIoC;
import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Game;
import daw.examen.domain.service.GameService;
import daw.examen.domain.entinty.Character;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService = GameIoC.getGameService();

    @GetMapping()
    public String findAll(Model model) {

        // To see the list of movies
        model.addAttribute("gameList", gameService.findAll());

        return "games.html";
    }

    @GetMapping("/{code}")
    public String findByCode(Model model, @PathVariable("code") String game_code) {

        // To see the movie details
        model.addAttribute("game", gameService.findByCode(game_code));

        return "gameDetail.html";
    }

    @PostMapping()
    public String newGame() {

        Game game = new Game("MI3-2022", "Return to Monkey Island", 2022, "A new adventure of Guybrush Threepwood",
                new Director(1, null, null));

        List<Character> characterList = List.of(
                new Character(1, null, null, null),
                new Character(2, null, null, null),
                new Character(3, null, null, null));

        game.setCharacterList(characterList);

        gameService.add(game);

        return "games.html";
    }
}