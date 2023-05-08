package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.TracesItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TracesItemsRepository extends JpaRepository<TracesItems, Long> {

    Optional<TracesItems> findByName(String name);
}
