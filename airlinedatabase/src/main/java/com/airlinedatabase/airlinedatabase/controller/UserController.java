package com.airlinedatabase.airlinedatabase.controller;

import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Users;
import com.airlinedatabase.airlinedatabase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@ComponentScan(basePackages = "com")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUsers")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void addUsers(@RequestBody Users newUser) {
        userService.createUser(newUser);
    }

    @GetMapping("/readAllUsers")
    public Iterable<Users> readAllUsers() {
        return userService.displayAllUsers();
    }

    @PutMapping("/updateUsers")
    @ExceptionHandler(RecordNotFoundException.class)
    public void updateUsers(@RequestBody Users updateUsers) {
        userService.updateUser(updateUsers);
    }

    @GetMapping("/searchUsers/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> searchUserById(@PathVariable("id")BigInteger userId) {
        return userService.findUserById(userId);
    }

    @DeleteMapping("/deleteUsers/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public void deleteBookingById(@PathVariable("id") BigInteger userId){
        userService.deleteUser(userId);
    }

}
