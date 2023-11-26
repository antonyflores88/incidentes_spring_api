package sv.dev.api.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sv.dev.api.models.entity.Incidents;
import sv.dev.api.models.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {

	Status findFirstByIncidentOrderByStartTimeDesc(Incidents incident);
	
	Iterable<Status> findByIncidentOrderByStartTime(Incidents incident);
	
	List<Status> findByIncidentIdOrderByStartTime(Integer incidentId);
}
