package com.crud.practice.userService;


import com.crud.practice.userModel.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.practice.userRepository.userRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private final userRepository userRepo;

    @Autowired
    public UserServices(userRepository userRepo){
        this.userRepo = userRepo;
    }

    public UserModel saveUser(UserModel userModel){
        return userRepo.save(userModel);
    }

    public List<UserModel> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<UserModel> getUserById(Long id){
        return userRepo.findById(id);
    }

    public UserModel updateUser(Long id, UserModel updatedUser) throws Exception
    {
        Optional<UserModel> existingUser = userRepo.findById(id);
        if(existingUser.isPresent()){
            UserModel user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setSubjects(updatedUser.getSubjects());
            return userRepo.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void addSubject(Long id, String subject){
        Optional<UserModel> existingUser = userRepo.findById(id);
        if(existingUser.isPresent()){
            UserModel user = existingUser.get();
            user.addSubject(subject);
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void removeSubject(Long id, String subject){
        Optional<UserModel> existingUser = userRepo.findById(id);
        if(existingUser.isPresent()){
            UserModel user = existingUser.get();
            user.removeSubject(subject);
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
}
