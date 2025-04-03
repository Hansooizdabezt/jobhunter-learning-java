package vn.hoidanit.jobhunter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User createUser) {
        User newUser = this.userService.handleCreateUser(createUser);
        return newUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.handleDeleteUserById(id);
        return "delete user successfully";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.handleGetAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return this.userService.handleGetUserById(id);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User updateUser) {
        return this.userService.handleUpdateUser(id, updateUser);
    }

}
