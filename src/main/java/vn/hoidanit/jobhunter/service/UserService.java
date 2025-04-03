package vn.hoidanit.jobhunter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void handleDeleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    public List<User> handleGetAllUsers() {
        return this.userRepository.findAll();
    }

    public User handleGetUserById(long id) {
        return this.userRepository.getUserById(id);
    }

    public User handleUpdateUser(long id, User updateUser) {
        User existingUser = this.userRepository.getUserById(id);
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setName(updateUser.getName());
        existingUser.setPassword(updateUser.getPassword());
        return this.userRepository.save(existingUser);
    }

}
