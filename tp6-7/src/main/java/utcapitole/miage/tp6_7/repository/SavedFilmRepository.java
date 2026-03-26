package utcapitole.miage.tp6_7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage.tp6_7.entity.SavedFilm;
import utcapitole.miage.tp6_7.entity.User;

import java.util.List;
import java.util.Optional;

public interface SavedFilmRepository extends JpaRepository<SavedFilm, Long> {

    Optional<SavedFilm> findByTmdbFilmIdAndUser(Long tmdbFilmId, User user);

    List<SavedFilm> findByUser(User user);

}
