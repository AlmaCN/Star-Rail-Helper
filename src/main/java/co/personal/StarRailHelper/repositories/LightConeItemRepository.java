package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.LightConeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LightConeItemRepository extends JpaRepository<LightConeItem, Long> {

    Optional<LightConeItem> findByName(String name);
}
