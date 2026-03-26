package utcapitole.miage.tp6_7.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utcapitole.miage.tp6_7.dto.FilmPageDTO;
import utcapitole.miage.tp6_7.entity.User;
import utcapitole.miage.tp6_7.service.FilmService;
import utcapitole.miage.tp6_7.service.SavedFilmService;

@Controller
public class FilmWebController {

    private final FilmService filmService;
    private final SavedFilmService savedFilmService;

    public FilmWebController(FilmService filmService, SavedFilmService savedFilmService) {
        this.filmService = filmService;
        this.savedFilmService = savedFilmService;
    }

    @GetMapping("/films")
    public String showPopularFilms(@RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {
        FilmPageDTO filmPage = filmService.getPopularFilms(page);
        model.addAttribute("filmPage", filmPage);
        addSessionUserData(model, session);
        return "films";
    }

    @GetMapping("/film/{id}")
    public String showFilmDetails(@PathVariable int id, Model model, HttpSession session) {
        var film = filmService.getFilmById(id);
        model.addAttribute("film", film);
        addSessionUserData(model, session);

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("isFavorite", savedFilmService.isFavorite(loginUser, film.getId()));
            model.addAttribute("isToWatch", savedFilmService.isToWatch(loginUser, film.getId()));
        }

        return "film-detail";
    }

    @PostMapping("/film/save")
    public String saveFilmForUser(@RequestParam Long tmdbFilmId,
                                  @RequestParam String title,
                                  @RequestParam(required = false) String posterPath,
                                  @RequestParam String action,
                                  @RequestParam String redirectTo,
                                  HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        savedFilmService.saveFilmForUser(loginUser, tmdbFilmId, title, posterPath, action);
        return "redirect:" + redirectTo;
    }

    @PostMapping("/film/remove")
    public String removeFilmAction(@RequestParam Long tmdbFilmId,
                                   @RequestParam String action,
                                   @RequestParam String redirectTo,
                                   HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        savedFilmService.removeActionForUser(loginUser, tmdbFilmId, action);
        return "redirect:" + redirectTo;
    }

    private void addSessionUserData(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        if (loginUser != null) {
            model.addAttribute("favoriteFilms", savedFilmService.getFavoriteFilm(loginUser));
            model.addAttribute("toWatchFilms", savedFilmService.getToWatchFilm(loginUser));
        }
    }
}
