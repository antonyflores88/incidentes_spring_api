package sv.dev.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.dev.api.models.entity.Comment;
import sv.dev.api.models.entity.Incidents;
import sv.dev.api.models.entity.Status;
import sv.dev.api.models.entity.User;
import sv.dev.api.models.service.iCommentService;
import sv.dev.api.models.service.iIncidentsService;
import sv.dev.api.models.service.iStatusService;
import sv.dev.api.models.service.iUserService;
import sv.dev.api.utils.IdGenerator;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

	@Autowired
    private final iIncidentsService incidentsService;
	@Autowired
	private final iUserService userService;
	@Autowired
    private iStatusService statusService;
	@Autowired
	private final iCommentService commentService;


    	public IncidentController(iIncidentsService incidentsService, iUserService userService, 
    			iCommentService commentService,iStatusService statusService) {
            this.incidentsService = incidentsService;
            this.userService = userService;
            this.commentService = commentService;
            this.statusService = statusService;
        }
    	
////////////////////////////////////////////////////////////////

    @PostMapping
    public Incidents createIncidents(@RequestBody Incidents incidents) {
    	
    	 User assignedUser = userService.getUserById(1); // Replace with appropriate method to get the assigned user
    	    incidents.setAssignedTo(assignedUser);
    	    incidents.setCreatedAt(LocalDateTime.now());
    	    
    	 // Generate a unique incident ID
    	    int uniqueIncidentID = IdGenerator.generateIncidentID();
    	    incidents.setId(uniqueIncidentID); // Set the generated ID for the incident
    	 // Setting initial status as In Progress   
    	    incidents.setStatus("In progress");
    	    incidents.setPriority("Media");
    	    
        return incidentsService.createIncidents(incidents);
    }
    
////////////////////////////////////////////////////////////////


    @GetMapping("/{id}")
    public Incidents getIncidentsById(@PathVariable Integer id) {
        return incidentsService.getIncidentsById(id);
    }
    
////////////////////////////////////////////////////////////////

    @GetMapping
    public Iterable<Incidents> getAllIncidents() {
        return incidentsService.getAllIncidents();
    }
    
////////////////////////////////////////////////////////////////

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncidentsAndComments(@PathVariable Integer id, 
            @RequestBody Incidents updatedIncident) {
        
        Incidents existingIncident = incidentsService.getIncidentsById(id);
        
        // Check if the ticket is already resolved
        if (existingIncident.isResolved()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket is already resolved. Further updates are not allowed.");
        }

        if (existingIncident != null && updatedIncident != null
                && updatedIncident.getStatus() != null 
                && updatedIncident.getPriority() != null) {

        	// Check if the status is "Resolved" and set the resolvedDate accordingly
            if ("Resolved".equals(updatedIncident.getStatus())) {
                existingIncident.setResolvedDate(LocalDateTime.now().withNano(0));
                existingIncident.setResolved(true);
            }
            
         // Update fields based on your requirements
            existingIncident.setStatus(updatedIncident.getStatus());
            existingIncident.setPriority(updatedIncident.getPriority());

            // Save the changes to the incident
            incidentsService.updateIncident(existingIncident);

            // Return the updated incident or handle the response as needed
            // For simplicity, I'm returning the updated incident here
            return new ResponseEntity<>(existingIncident, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
////////////////////////////////////////////////////////////////

    @GetMapping("/comments/{incidentId}")
    public ResponseEntity<List<Comment>> getCommentsByIncidentId(@PathVariable Integer incidentId) {
        List<Comment> comments = commentService.getCommentsByIncidentId(incidentId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    
////////////////////////////////////////////////////////////////
  
    @PostMapping("/comments/{incidentId}")
    public ResponseEntity<Comment> addComment(@PathVariable Integer incidentId,
            @RequestBody Comment newComment) {
    	
    	 Comment existingComment = commentService.getCommentByIncidentId(incidentId);
        // Asigna el incident_id, user_id, is_technician y created_at al nuevo comentario
        newComment.setIncident(existingComment.getIncident());  // Suponiendo que tienes la instancia de Incidents disponible
        newComment.setUser(existingComment.getUser());  // Suponiendo que tienes la instancia de User disponible
        newComment.setTechnicianComment(newComment.isTechnicianComment());  // O false dependiendo de tus necesidades
        newComment.setCreatedAt(LocalDateTime.now().withNano(0));

        // Guarda el nuevo comentario
        commentService.saveComment(newComment);

        // Por simplicidad, estoy devolviendo el nuevo comentario aquí
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
    
 ////////////////////////////////////////////////////////////////
    
    @GetMapping("/{id}/status-times")
    public ResponseEntity<List<Map<String, Object>>> getStatusTimes(@PathVariable Integer id) {
        List<Status> statusList = statusService.getStatusListForIncident(id);
        
        int totalTimeSum = statusList.stream()
                .mapToInt(Status::getTotalTime)
                .sum();
        
        String formattedTotalTime = formatTime(totalTimeSum);
        
        List<Map<String, Object>> statusTimes = statusList.stream()
                .map(status -> {
                 Map<String, Object> statusTime = Map.of(
                 "status", status.getStatus(),
                 "totalTime", formatTime(status.getTotalTime())
          );
         return statusTime;
      })
      .collect(Collectors.toList());

        Map<String, Object> sumMap = Map.of("totalTimeSum", formattedTotalTime);

        List<Map<String, Object>> responseList = new ArrayList<>();
        responseList.add(sumMap);
        responseList.addAll(statusTimes);

        return ResponseEntity.ok(responseList);
        
    }
    
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

}
