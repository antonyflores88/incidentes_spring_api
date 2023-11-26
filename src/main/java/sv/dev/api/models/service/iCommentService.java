package sv.dev.api.models.service;

import java.util.List;

import sv.dev.api.models.entity.Comment;

public interface iCommentService {
	
	public void saveComment(Comment comment);
	
	List<Comment> getCommentsByIncidentId(Integer incidentId);

	Comment getCommentByIncidentId(Integer incidentId);

}
