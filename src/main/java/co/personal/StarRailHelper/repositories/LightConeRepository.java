package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.LightCone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LightConeRepository extends JpaRepository<LightCone, Long> {

    Optional<LightCone> findByName(String name);
}
