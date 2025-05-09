package fact.it.angular.repository;

import fact.it.angular.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

