package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.Relics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelicsRepository extends JpaRepository<Relics, Long> {

    Optional<Relics> findByName(String name);
}
