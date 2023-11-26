package sv.dev.api.models.service;

import org.springframework.stereotype.Service;

import sv.dev.api.models.entity.User;
import sv.dev.api.models.repository.UserRepository;


@Service
public class UserServiceImpl implements iUserService {

	private final UserRepository userRepository;

    // Constructor injection of the repository
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
