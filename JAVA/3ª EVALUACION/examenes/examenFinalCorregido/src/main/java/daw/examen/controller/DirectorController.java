package daw.examen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import daw.examen.common.IoC.GameIoC;
import daw.examen.domain.service.GameService;

@Controller
@RequestMapping("/directors")
public class DirectorController {

    GameService gameService;

    public DirectorController() {
        this.gameService = GameIoC.getGameService();
    }

    @GetMapping("/{id}/games")
    public String findAllGamesByDirectorId(Model model, @PathVariable Integer id) {

        model.addAttribute("gameList", gameService.findAllByDirectorId(id));

        return "games.html";
    }
}