package sv.dev.api.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incidents incident; // Assuming Incident is the entity representing the incident

    private String status;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "total_time")
    private Integer totalTime;
    

    // Constructor(s), getters, setters, and other methods to be added...
    
	/**
	 * @return the totalTime
	 */
	public Integer getTotalTime() {
		return totalTime;
	}
	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the incident
	 */
	public Incidents getIncident() {
		return incident;
	}
	/**
	 * @param incident the incident to set
	 */
	public void setIncident(Incidents incident) {
		this.incident = incident;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the startTime
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "Status [id=" + id + ", incident=" + incident + ", status=" + status + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", totalTime=" + totalTime + "]";
	}
    
    
}