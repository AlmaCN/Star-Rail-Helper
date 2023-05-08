package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.RelicItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelicItemRepository extends JpaRepository<RelicItem, Long> {

    Optional<RelicItem> findByName(String name);
}
