package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spr.model.User;
import spr.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/rest")
public class AdminRESTController {

    private final UserService userService;

    @Autowired
    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        return users;
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public User getUserByName(@PathVariable("userName") String userName) {
        return userService.getUserByName(userName);
    }

    @PostMapping("/add")
    public ResponseEntity.BodyBuilder addUser(@RequestBody User user) {
        if (userService.addUser(user)) {
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.badRequest();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity.BodyBuilder deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        if (userService.getUserById(id) == null)
            return ResponseEntity.ok();
        else
            return ResponseEntity.badRequest();
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
            return userService.updateUser(user);
    }
}
