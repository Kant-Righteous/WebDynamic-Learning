package utcapitole.miage._026examtd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage._026examtd.model.Sport;

import java.util.List;

public interface SportRepository extends JpaRepository<Sport,Integer> {

    Sport findById(int id);

}
