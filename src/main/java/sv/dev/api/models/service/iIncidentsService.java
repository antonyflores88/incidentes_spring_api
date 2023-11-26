package sv.dev.api.models.service;

import java.util.Map;

import sv.dev.api.models.entity.Incidents;

public interface iIncidentsService {

	public Incidents createIncidents(Incidents incidents);

	public Incidents getIncidentsById(Integer id);

	public Iterable<Incidents> getAllIncidents();

	public void deleteIncidentsById(Integer id);
	
	public void updateIncident(Incidents incident);
	
	Map<String, String> calculateStatusTimes(Integer incidentId);

}
