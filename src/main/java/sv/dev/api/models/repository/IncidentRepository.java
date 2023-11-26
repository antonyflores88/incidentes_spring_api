package sv.dev.api.models.repository;

import org.springframework.data.repository.CrudRepository;

import sv.dev.api.models.entity.Incidents;

public interface IncidentRepository extends CrudRepository<Incidents, Integer> {

}
