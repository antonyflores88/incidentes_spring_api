package sv.dev.api.models.service;

import sv.dev.api.models.entity.Incidents;
import sv.dev.api.models.entity.Status;
import sv.dev.api.models.repository.StatusRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class StatusServiceImpl implements iStatusService {

	private final StatusRepository statusRepository;

    // Constructor injection of the repository
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status updateStatus(Incidents incident, String newStatus) {
    	
    	Status currentStatus = statusRepository.findFirstByIncidentOrderByStartTimeDesc(incident);

    		    if (currentStatus != null) {
    		        // Set end time for the current status as the current timestamp
    		        currentStatus.setEndTime(LocalDateTime.now());

    		        // Create a new status entry with the start time for the new status
    		        Status newStatusEntry = new Status();
    		        newStatusEntry.setStartTime(LocalDateTime.now());
    		        newStatusEntry.setStatus(newStatus);
    		        newStatusEntry.setIncident(incident);

    		        // Save the updated current status and the new status entry
    		        statusRepository.save(currentStatus);
    		        statusRepository.save(newStatusEntry);

    		        // Return the new status entry or confirmation
    		        return newStatusEntry;
    		    } else {
    		        // Handle the case where the current status is not found
    		        return null;
    		    }
    }

    @Override
    public Iterable<Status> getStatusHistory(Incidents incident) {
        // Retrieve the status history for the provided incident from the database
        return statusRepository.findByIncidentOrderByStartTime(incident);
    }
    
    @Override
    public List<Status> getStatusListForIncident(Integer incidentId) {
        return statusRepository.findByIncidentIdOrderByStartTime(incidentId);
    }

}
