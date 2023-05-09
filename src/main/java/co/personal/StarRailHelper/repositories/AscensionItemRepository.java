package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.AscensionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AscensionItemRepository extends JpaRepository<AscensionItem, Long> {

    Optional<AscensionItem> findByName(String name);
}
