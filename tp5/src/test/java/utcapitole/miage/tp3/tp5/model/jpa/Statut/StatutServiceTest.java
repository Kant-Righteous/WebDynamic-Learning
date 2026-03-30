package utcapitole.miage.tp3.tp5.model.jpa.Statut;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utcapitole.miage.tp3.tp5.model.Statut;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatutServiceTest {

    @Mock
    private StatutRepository statutRepository;

    @InjectMocks
    private StatutService statutService;

    @Test
    void findAll() {
        List<Statut> statuts = List.of(new Statut(), new Statut());
        when(statutRepository.findAll()).thenReturn(statuts);

        assertSame(statuts, statutService.findAll());
    }

    @Test
    void findByIdReturnsStatutWhenPresent() {
        Statut statut = new Statut();
        when(statutRepository.findById(1)).thenReturn(Optional.of(statut));

        assertSame(statut, statutService.findById(1));
    }

    @Test
    void findByIdReturnsNullWhenMissing() {
        when(statutRepository.findById(99)).thenReturn(Optional.empty());

        assertNull(statutService.findById(99));
    }
}
