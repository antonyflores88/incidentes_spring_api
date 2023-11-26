package sv.dev.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.dev.api.models.entity.Comment;
import sv.dev.api.models.repository.CommentRepository;

@Service
public class CommentServiceImpl implements iCommentService {
	
	@Autowired
    private CommentRepository commentRepository;

	 @Override
	    public void saveComment(Comment comment) {
	        commentRepository.save(comment);
	    }
	 
	 @Override
	    public List<Comment> getCommentsByIncidentId(Integer incidentId) {
	        return commentRepository.findByIncidentId(incidentId);
	    }
	 
	 @Override
	 public Comment getCommentByIncidentId(Integer incidentId) {
	        // Obtén un comentario específico asociado al incidente
	        List<Comment> comments = commentRepository.findByIncidentId(incidentId);

	        // Devuelve el primer comentario o null si no hay comentarios
	        return comments.isEmpty() ? null : comments.get(0);
	    }
}
