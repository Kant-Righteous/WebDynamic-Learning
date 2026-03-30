package utcapitole.miage.tp3.tp5.model.jpa.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import utcapitole.miage.tp3.tp5.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void findAll() {
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        assertSame(users, userService.findAll());
    }

    @Test
    void findByIdReturnsUserWhenPresent() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertSame(user, userService.findById(1));
    }

    @Test
    void findByIdReturnsNullWhenMissing() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertNull(userService.findById(99));
    }

    @Test
    void saveUserEncodesPasswordAndSavesUser() {
        User user = new User();
        user.setEmail("alice@example.com");
        when(userRepository.findByEmail("alice@example.com")).thenReturn(null);
        when(passwordEncoder.encode("plain-password")).thenReturn("encoded-password");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user, "plain-password");

        assertSame(user, savedUser);
        assertEquals("encoded-password", user.getPassword());
        verify(passwordEncoder).encode("plain-password");
        verify(userRepository).save(user);
    }

    @Test
    void saveUserThrowsWhenEmailAlreadyExists() {
        User existingUser = new User();
        User newUser = new User();
        newUser.setEmail("alice@example.com");
        when(userRepository.findByEmail("alice@example.com")).thenReturn(existingUser);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.saveUser(newUser, "plain-password")
        );

        assertNotNull(exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void verifyLoginReturnsUserWhenPasswordMatches() {
        User user = new User();
        user.setPassword("encoded-password");
        when(userRepository.findByEmail("alice@example.com")).thenReturn(user);
        when(passwordEncoder.matches("plain-password", "encoded-password")).thenReturn(true);

        assertSame(user, userService.verifyLogin("alice@example.com", "plain-password"));
    }

    @Test
    void verifyLoginReturnsNullWhenUserDoesNotExist() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);

        assertNull(userService.verifyLogin("unknown@example.com", "plain-password"));
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void verifyLoginReturnsNullWhenPasswordDoesNotMatch() {
        User user = new User();
        user.setPassword("encoded-password");
        when(userRepository.findByEmail("alice@example.com")).thenReturn(user);
        when(passwordEncoder.matches("wrong-password", "encoded-password")).thenReturn(false);

        assertNull(userService.verifyLogin("alice@example.com", "wrong-password"));
    }
}
