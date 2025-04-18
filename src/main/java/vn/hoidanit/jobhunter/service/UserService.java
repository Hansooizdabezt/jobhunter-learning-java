package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.controller.HelloController;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {

    private final HelloController helloController;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, HelloController helloController,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.helloController = helloController;
        this.passwordEncoder = passwordEncoder;
    }

    public User handleCreateUser(User user) {
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return this.userRepository.save(user);
    }

    public void handleDeleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    public List<User> handleGetAllUsers() {
        return this.userRepository.findAll();
    }

    public User handleGetUserById(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public User handleUpdateUser(User updateUser) {
        User existingUser = this.handleGetUserById(updateUser.getId());
        if (existingUser != null) {
            existingUser.setEmail(updateUser.getEmail());
            existingUser.setName(updateUser.getName());
            existingUser.setPassword(updateUser.getPassword());
            existingUser = this.userRepository.save(existingUser);
        }
        return existingUser;

    }

}
