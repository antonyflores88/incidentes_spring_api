package sv.dev.api.models.service;

import sv.dev.api.models.entity.User;

public interface iUserService {
	
	public User createUser(User user);
    
    public User getUserById(Integer id);
    
    public User getUserByEmail(String email);
    
    public Iterable<User> getAllUsers();
    
    public User updateUser(User user);
    
    public void deleteUserById(Integer id);
    

}
