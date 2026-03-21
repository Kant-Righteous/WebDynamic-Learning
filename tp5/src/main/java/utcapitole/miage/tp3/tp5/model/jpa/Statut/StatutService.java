package utcapitole.miage.tp3.tp5.model.jpa.Statut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcapitole.miage.tp3.tp5.model.Statut;

import java.util.List;

@Service
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    public List<Statut> findAll() {
        return statutRepository.findAll();
    }

    public Statut findById(Integer id) {
        return statutRepository.findById(id).orElse(null);
    }

}
