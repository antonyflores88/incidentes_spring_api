package sv.dev.api.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sv.dev.api.models.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
	
	List<Comment> findByIncidentId(Integer incidentId);
	
}
