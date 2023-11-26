package sv.dev.api.models.repository;

import org.springframework.data.repository.CrudRepository;

import sv.dev.api.models.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);
	
}
