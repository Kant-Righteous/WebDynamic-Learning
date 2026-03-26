package utcapitole.miage.tp6_7.service;

import org.springframework.stereotype.Service;
import utcapitole.miage.tp6_7.entity.User;
import utcapitole.miage.tp6_7.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //register
    public User registerUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        return userRepository.save(newUser);
    }

    //login
    public User loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return user.get();//该user不是真“user”，而是Optional user所以要get
            }
        }

        throw new RuntimeException("User does not exist or wrong password");
    }

}
