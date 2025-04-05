package vn.hoidanit.jobhunter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.utils.errors.IdInvalidException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User createUser) {
        User newUser = this.userService.handleCreateUser(createUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        if (id >= 100) {
            throw new IdInvalidException("User not found");
        }
        this.userService.handleDeleteUserById(id);
        return ResponseEntity.ok("Delete user successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listUser = this.userService.handleGetAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = this.userService.handleGetUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User updateUser) {
        User currentUser = this.userService.handleUpdateUser(updateUser);
        return ResponseEntity.ok(currentUser);
    }

}
