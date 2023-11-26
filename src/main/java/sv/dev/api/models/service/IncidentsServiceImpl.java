package sv.dev.api.models.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.dev.api.models.entity.Incidents;
import sv.dev.api.models.entity.Status;
import sv.dev.api.models.repository.IncidentRepository;


@Service
public class IncidentsServiceImpl implements iIncidentsService {
	
	@Autowired
    private iStatusService statusService;

	private final IncidentRepository incidentRepository;

    // Constructor injection of the repository
    public IncidentsServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Incidents createIncidents(Incidents incidents) {
        return incidentRepository.save(incidents);
    }

    @Override
    public Incidents getIncidentsById(Integer id) {
        return incidentRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Incidents> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @Override
    public void deleteIncidentsById(Integer id) {
        incidentRepository.deleteById(id);
    }
    
    @Override
    public void updateIncident(Incidents incident) {
        incidentRepository.save(incident);
    }
    
    @Override
    public Map<String, String> calculateStatusTimes(Integer incidentId) {
        List<Status> statusList = statusService.getStatusListForIncident(incidentId);
        return calculateStatusTimesFromList(statusList);
    }

    private Map<String, String> calculateStatusTimesFromList(List<Status> statusList) {
        Map<String, String> statusTimes = new HashMap<>();
        int totalElapsedTime = 0;

        for (int i = 0; i < statusList.size(); i++) {
            Status currentStatus = statusList.get(i);
            String statusName = currentStatus.getStatus();

            // Assuming totalTime is the time spent in seconds
            int totalTime = currentStatus.getTotalTime();

            // Convert totalTime to HH:MM:SS format
            String formattedTime = formatTime(totalTime);

            statusTimes.put(statusName, formattedTime);
            totalElapsedTime += totalTime;
        }

     // Convert totalElapsedTime to HH:MM:SS format
        String formattedTotalTime = formatTime(totalElapsedTime);
        statusTimes.put("Total", formattedTotalTime);

        return statusTimes;
    }
    
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

}
