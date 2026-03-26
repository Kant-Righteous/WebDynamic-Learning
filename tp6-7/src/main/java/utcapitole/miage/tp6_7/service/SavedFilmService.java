package utcapitole.miage.tp6_7.service;

import org.springframework.stereotype.Service;
import utcapitole.miage.tp6_7.entity.SavedFilm;
import utcapitole.miage.tp6_7.entity.User;
import utcapitole.miage.tp6_7.repository.SavedFilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SavedFilmService {

    private final SavedFilmRepository savedFilmRepository;

    public SavedFilmService(SavedFilmRepository savedFilmRepository) {
        this.savedFilmRepository = savedFilmRepository;
    }

    /**
     * @param user
     * @param tmdbFilmId
     * @param title
     * @param posterPath
     * @param action the action of user(fav, watch)
    **/
    public void saveFilmForUser(User user, Long tmdbFilmId, String title, String posterPath, String action) {
        Optional<SavedFilm> savedFilm = savedFilmRepository.findByTmdbFilmIdAndUser(tmdbFilmId, user);
        SavedFilm filmToSave;
        if (savedFilm.isPresent()) {
            filmToSave = savedFilm.get();
        } else {
            filmToSave = new SavedFilm();
            filmToSave.setTmdbFilmId(tmdbFilmId);
            filmToSave.setTitle(title);
            filmToSave.setPosterPath(posterPath);
            filmToSave.setUser(user);
        }

        if ("FAVORITE".equals(action)) {
            filmToSave.setFavorite(true);
        } else if ("WATCH".equals(action)) {
            filmToSave.setToWatch(true);
        }

        savedFilmRepository.save(filmToSave);
    }

    public void removeActionForUser(User user, Long tmdbFilmId, String action) {
        Optional<SavedFilm> savedFilm = savedFilmRepository.findByTmdbFilmIdAndUser(tmdbFilmId, user);
        if (savedFilm.isEmpty()) {
            return;
        }

        SavedFilm film = savedFilm.get();
        if ("FAVORITE".equals(action)) {
            film.setFavorite(false);
        } else if ("WATCH".equals(action)) {
            film.setToWatch(false);
        }

        if (!film.isFavorite() && !film.isToWatch()) {
            savedFilmRepository.delete(film);
            return;
        }

        savedFilmRepository.save(film);
    }

    public List<SavedFilm> getFavoriteFilm(User user) {
        List<SavedFilm> savedFilms = savedFilmRepository.findByUser(user);
        return savedFilms.stream().filter(SavedFilm::isFavorite).toList();
    }

    public List<SavedFilm> getToWatchFilm(User user) {
        List<SavedFilm> savedFilms = savedFilmRepository.findByUser(user);
        return savedFilms.stream().filter(SavedFilm::isToWatch).toList();
    }

    public boolean isFavorite(User user, Long tmdbFilmId) {
        return savedFilmRepository.findByTmdbFilmIdAndUser(tmdbFilmId, user)
                .map(SavedFilm::isFavorite)
                .orElse(false);
    }

    public boolean isToWatch(User user, Long tmdbFilmId) {
        return savedFilmRepository.findByTmdbFilmIdAndUser(tmdbFilmId, user)
                .map(SavedFilm::isToWatch)
                .orElse(false);
    }
}
