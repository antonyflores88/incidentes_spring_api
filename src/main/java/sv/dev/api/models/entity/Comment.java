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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incidents incident; // Assuming Incident is the entity representing the incident

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Assuming User is the entity representing the user who made the comment

    @Column(name = "comment_text")
    private String commentText;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "is_technician")
    private boolean isTechnicianComment; // For distinguishing technician/user comments
    

    // Constructor(s), getters, setters, and other methods to be added...

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	 * @return the isTechnicianComment
	 */
	public boolean isTechnicianComment() {
		return isTechnicianComment;
	}

	/**
	 * @param isTechnicianComment the isTechnicianComment to set
	 */
	public void setTechnicianComment(boolean isTechnicianComment) {
		this.isTechnicianComment = isTechnicianComment;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", incident=" + incident + ", user=" + user + ", commentText=" + commentText
				+ ", createdAt=" + createdAt + ", isTechnicianComment=" + isTechnicianComment + "]";
	}
    
    

}