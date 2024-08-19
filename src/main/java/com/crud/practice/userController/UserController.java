package com.crud.practice.userController;


import com.crud.practice.userModel.UserModel;
import com.crud.practice.userService.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserServices userService;

    @Autowired
    public UserController(UserServices userService){
        this.userService = userService;
    }

    @PostMapping("/newuser")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
        UserModel newUser = userService.saveUser(userModel);
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id){
        Optional<UserModel> foundUser = userService.getUserById(id);

        if(foundUser.isPresent()){
            return ResponseEntity.ok().body(foundUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> allUsers = userService.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<Optional<UserModel>> updateUser(@PathVariable Long id, @RequestBody UserModel updatedUser) throws Exception {

        Optional<UserModel> foundUser = userService.getUserById(id);
        if(foundUser.isPresent()){
            foundUser = Optional.ofNullable(userService.updateUser(id, updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(foundUser);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        Optional<UserModel> deletedUser = userService.getUserById(id);
        if(deletedUser.isPresent())
        {
            userService.deleteUser(id);
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("User deleted successfully" + deletedUser);
    }


    @PutMapping("user/removesub/{id}")
    public ResponseEntity<Optional<UserModel>> addSubject(@PathVariable Long id, @RequestBody String subject)
    {
        Optional<UserModel> foundUser = userService.getUserById(id);
        if(foundUser.isPresent()){
            userService.addSubject(id, subject);
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(foundUser);
    }

    @PutMapping("user/addsub/{id}")
    public ResponseEntity<Optional<UserModel>> removeSubject(@PathVariable Long id, @RequestBody String subject){
        Optional<UserModel> foundUser = userService.getUserById(id);
        if(foundUser.isPresent()){
            userService.removeSubject(id, subject);
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(foundUser);
    }
}
