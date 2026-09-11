package utcapitole.miage._026examtd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcapitole.miage._026examtd.model.Sport;
import utcapitole.miage._026examtd.model.User;
import utcapitole.miage._026examtd.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;
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
                return user.get();
            }
        }

        throw new RuntimeException("User does not exist or wrong password");
    }

    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    //list of potential friends
    public List<User> findAllExceptMe(String username) {
        List<User> potentialFriends = userRepository.findAll();
        potentialFriends.removeIf(u -> u.getUsername().equals(username));
        return potentialFriends;
    }

    //list of friends
    public List<User> findAllFriends(String username) {
        return getUser(username).getMyFriends();
    }

    //add friend
    public boolean addFriend(String myUsername, String friendUsername) {
        return getUser(myUsername).getMyFriends().add(getUser(friendUsername));
    }

    //get sports list
    public List<Sport> findByUserName(String username) {
        User user = getUser(username);
        return user.getDoSport();
    }

}
