package utcapitole.miage.tp3.tp4.model.jpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcapitole.miage.tp3.tp4.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findWithParticipatedConferencesById(Integer id) {
        return userRepository.findWithParticipatedConferencesById(id).orElse(null);
    }
}
