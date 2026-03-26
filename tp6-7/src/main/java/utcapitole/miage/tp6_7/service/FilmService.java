package utcapitole.miage.tp6_7.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utcapitole.miage.tp6_7.dto.FilmDTO;
import utcapitole.miage.tp6_7.dto.FilmPageDTO;

@Service
public class FilmService {

    @Value("${tmdb.api-key}")
    private String apiKey;

    @Value("${tmdb.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public FilmService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FilmPageDTO getPopularFilms(int page) {
        String url = this.baseUrl + "/movie/popular?api_key=" + apiKey + "&page=" + page + "&language=fr-FR";

        return this.restTemplate.getForObject(url, FilmPageDTO.class);
    }

    public FilmDTO getFilmById(long id) {
        String url = this.baseUrl + "/movie/" + id + "?api_key=" + apiKey + "&language=fr-FR";

        return this.restTemplate.getForObject(url, FilmDTO.class);
    }
}
