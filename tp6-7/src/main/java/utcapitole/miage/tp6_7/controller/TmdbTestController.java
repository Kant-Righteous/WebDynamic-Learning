package utcapitole.miage.tp6_7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utcapitole.miage.tp6_7.dto.FilmDTO;
import utcapitole.miage.tp6_7.dto.FilmPageDTO;
import utcapitole.miage.tp6_7.service.FilmService;

@RestController
public class TmdbTestController {

    private final FilmService filmService;

    public TmdbTestController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/test/popular")
    public FilmPageDTO testGetPopularFilms(@RequestParam(defaultValue = "1") int page) {
        return filmService.getPopularFilms(page);
    }

    @GetMapping("/test/film/{id}")
    public FilmDTO testGetFilmById(@PathVariable long id) {
        return filmService.getFilmById(id);
    }

}
