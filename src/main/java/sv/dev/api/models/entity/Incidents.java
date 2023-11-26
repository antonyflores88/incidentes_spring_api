package sv.dev.api.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "incident")
public class Incidents implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer id;

    private String title;
    private String description;
    private String status;
    private String priority;
    
    @Column(name = "is_resolved")
    private boolean isResolved;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo; // Assuming User is the entity representing the assigned user

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "resolved_date")
    private LocalDateTime resolvedDate;
    
 // Constructor(s), getters, setters, and other methods to be added...
    
    
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return the isResolved
	 */
	public boolean isResolved() {
		return isResolved;
	}
	/**
	 * @param isResolved the isResolved to set
	 */
	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the assignedTo
	 */
	public User getAssignedTo() {
		return assignedTo;
	}
	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}
	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the resolvedDate
	 */
	public LocalDateTime getResolvedDate() {
		return resolvedDate;
	}
	/**
	 * @param resolvedDate the resolvedDate to set
	 */
	public void setResolvedDate(LocalDateTime resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	@Override
	public String toString() {
		return "Incidents [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", priority=" + priority + ", isResolved=" + isResolved + ", assignedTo=" + assignedTo
				+ ", createdAt=" + createdAt + ", resolvedDate=" + resolvedDate + "]";
	}
    

    
    
}
