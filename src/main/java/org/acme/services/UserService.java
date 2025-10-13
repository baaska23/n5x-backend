package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entities.User;
import org.acme.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
    
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Transactional
    public User createUser(String username, String email, String rawPassword) {
        String hash = BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setHashedPassword(hash);
        userRepository.persist(user);
        return user;
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public boolean checkPassword(User user, String rawPassword) {
        return BCrypt.checkpw(rawPassword, user.getHashedPassword());
    }
    
    public Optional<User> authenticate (String usernameOrEmail, String password) {
        Optional<User> userOpt = findByUsername(usernameOrEmail);
        if (userOpt.isEmpty()) {
            userOpt = findByEmail(usernameOrEmail);
        }
        
        if (userOpt.isPresent() && checkPassword(userOpt.get(), password)) {
            return userOpt;
        }
        
        return Optional.empty();
    }
}
