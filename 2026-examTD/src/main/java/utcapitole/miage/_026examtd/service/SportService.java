package utcapitole.miage._026examtd.service;

import org.springframework.stereotype.Service;
import utcapitole.miage._026examtd.model.Sport;
import utcapitole.miage._026examtd.model.User;
import utcapitole.miage._026examtd.repository.SportRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class SportService {

    private SportRepository sportRepository;
    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    //save sport for user
    public void saveSportForUser(User user, String sportName, LocalDate sportDate,double duration, double distance) {
        Sport sport = new Sport();
        sport.setSportName(sportName);
        sport.setDate(sportDate);
        sport.setDuration(duration);
        sport.setDistance(distance);
        sport.setUser(user);
        sportRepository.save(sport);
    }


    //delete sport for user
    public void deleteSport(int sid) {
        sportRepository.delete(sportRepository.findById(sid));
    }

}
