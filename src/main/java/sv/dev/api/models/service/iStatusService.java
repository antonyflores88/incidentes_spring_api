package sv.dev.api.models.service;

import java.util.List;

import sv.dev.api.models.entity.Incidents;
import sv.dev.api.models.entity.Status;

public interface iStatusService {

	Status updateStatus(Incidents incident, String newStatus);

    Iterable<Status> getStatusHistory(Incidents incident);
    
    public List<Status> getStatusListForIncident(Integer incidentId);
}
