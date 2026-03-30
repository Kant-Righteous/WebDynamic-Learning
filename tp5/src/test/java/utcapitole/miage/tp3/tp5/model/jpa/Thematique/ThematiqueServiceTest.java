package utcapitole.miage.tp3.tp5.model.jpa.Thematique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utcapitole.miage.tp3.tp5.model.Thematique;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ThematiqueServiceTest {

    @Mock
    private ThematiqueRepository thematiqueRepository;

    @InjectMocks
    private ThematiqueService thematiqueService;

    @Test
    void findAll() {
        List<Thematique> thematiques = List.of(new Thematique(), new Thematique());
        when(thematiqueRepository.findAll()).thenReturn(thematiques);

        assertSame(thematiques, thematiqueService.findAll());
    }
}
