package co.personal.StarRailHelper.repositories;

import co.personal.StarRailHelper.entites.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraceRepository extends JpaRepository<Trace, Long> {

    Optional<Trace> findByName(String name);
}
