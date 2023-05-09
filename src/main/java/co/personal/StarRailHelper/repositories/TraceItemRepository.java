package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.TraceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraceItemRepository extends JpaRepository<TraceItem, Long> {

    Optional<TraceItem> findByName(String name);
}
