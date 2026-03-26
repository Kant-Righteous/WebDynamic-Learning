package utcapitole.miage.tp3.tp5.model.jpa.Thematique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcapitole.miage.tp3.tp5.model.Thematique;

import java.util.List;

@Service
public class ThematiqueService {

    @Autowired
    private ThematiqueRepository thematiqueRepository;

    public List<Thematique> findAll() {
        return thematiqueRepository.findAll();
    }

}
