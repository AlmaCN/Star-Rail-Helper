package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.AscensionItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AscensionItemsRepository extends JpaRepository<AscensionItems, Long> {

    Optional<AscensionItems> findByName(String name);
}
